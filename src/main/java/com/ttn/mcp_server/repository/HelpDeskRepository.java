package com.ttn.mcp_server.repository;

import com.ttn.mcp_server.entity.HelpDeskTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelpDeskRepository extends JpaRepository<HelpDeskTicket, Integer> {

    @Query(value = "select hd.* from help_desk hd where hd.status in :status and hd.user_name = :username", nativeQuery = true)
    List<HelpDeskTicket> findAllByStatusAndUsername(List<String> status, String username);
}
