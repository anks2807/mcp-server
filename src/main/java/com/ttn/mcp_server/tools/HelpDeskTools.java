package com.ttn.mcp_server.tools;

import com.ttn.mcp_server.entity.HelpDeskTicket;
import com.ttn.mcp_server.repository.HelpDeskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class HelpDeskTools {

    private final HelpDeskRepository helpDeskRepository;

    public HelpDeskTools(HelpDeskRepository helpDeskRepository) {
        this.helpDeskRepository = helpDeskRepository;
    }

    @Tool(name = "create_help_desk_ticket", description = "Create a help desk ticket for a user")
    public String createTicket(@ToolParam(description = "Details to create a help desk ticket") String summary,
                               @ToolParam(description = "username") String username) {
        username = username != null ? username : "Anonymous";
        HelpDeskTicket helpDeskTicket = HelpDeskTicket.builder()
                .createdAt(LocalDateTime.now())
                .status("OPEN")
                .summary(summary)
                .username(username)
                .eta(LocalDateTime.now().plusDays(5))
                .build();
        HelpDeskTicket savedTicket = helpDeskRepository.save(helpDeskTicket);
        return "Ticket created for " + username + " with ticket id #: " + savedTicket.getHelpDeskId();
    }

    @Tool(name = "fetch_tickets_by_users", description = "Fetch the status of the tickets based on a given username")
    List<HelpDeskTicket> getTicketStatus(@ToolParam(description = "username") String username) {
        username = username != null ? username : "Anonymous";
        log.info("Fetching tickets for user: {}", username);
        List<HelpDeskTicket> tickets =  helpDeskRepository.findAllByStatusAndUsername(List.of("OPEN", "IN_PROGRESS"), username);
        log.info("Found {} tickets for user: {}", tickets.size(), username);
        return tickets;
    }


}
