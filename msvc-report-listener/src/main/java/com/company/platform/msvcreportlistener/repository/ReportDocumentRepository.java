package com.company.platform.msvcreportlistener.repository;

import com.company.platform.msvcreportlistener.documents.ReportDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDocumentRepository extends MongoRepository<ReportDocument, String> {
}
