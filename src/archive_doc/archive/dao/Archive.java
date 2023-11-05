package archive_doc.archive.dao;

import archive_doc.archive.model.Document;


import java.time.LocalDate;

public interface Archive {
    boolean addDocument (Document document);

    boolean updateDocument(int documentId,int folderId,String url);
    Document getDocFromFolder (int documentId, int folderId);

    Document[] getAllDocFromFolder(int folderId);

    Document[] getDocBetweenDate(LocalDate dateFrom,LocalDate dateTo);
    int size(); //quantity
    void viewDocuments();
}
