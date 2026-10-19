package com.company.procuraX.service;

import com.company.procuraX.model.AuditLog;
import com.company.procuraX.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void logAction(
            String username,
            String action,
            String entityType,
            String entityId,
            String details) {

        AuditLog auditLog = new AuditLog();

        auditLog.setUsername(username);
        auditLog.setAction(action);
        auditLog.setEntityType(entityType);
        auditLog.setEntityId(entityId);
        auditLog.setTimestamp(LocalDateTime.now());
        auditLog.setDetails(details);

        auditLogRepository.save(auditLog);
    }
}