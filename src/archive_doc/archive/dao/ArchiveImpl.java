package archive_doc.archive.dao;

import archive_doc.archive.model.Document;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class ArchiveImpl implements Archive {
    static Comparator<Document> comparator = (d1, d2) -> {
        int res = d1.getDate().compareTo(d2.getDate());
        return res != 0 ? res : Integer.compare(d1.getDocumentId(), d2.getDocumentId());
    };

    //
    private Document[] documents;
    private int size;


    public ArchiveImpl(int capacity) {
        documents = new Document[capacity];
    }

    public boolean addDocument (Document document) {
        if (document == null || size == documents.length || getDocFromFolder(document.getDocumentId(),
                document.getFolderId()) != null) {
            return false;
        }
        int index = Arrays.binarySearch(documents, 0, size, document, comparator);
        index = index >= 0 ? index : -index - 1;

        System.arraycopy(documents, index, documents, index + 1, size - index);
        documents[index] = document;
        size++;
        return true;

    }

    @Override
    public Document getDocFromFolder(int documentId, int folderId) {
        Document pattern = new Document(folderId, documentId, null, null, null);
        for (int i = 0; i < size; i++) {
            if (pattern.equals(documents[i])) {
                return documents[i];
            }
        }
        return null;
    }

    @Override
    public Document[] getAllDocFromFolder(int folderId) {
        return findByPredicate(p -> p.getFolderId() == folderId);
    }

    private Document[] findByPredicate(Predicate<Document> predicate) {
        Document[] res = new Document[size];
        int j = 0; // это счетчик найденных докумен
        for (int i = 0; i < size; i++) {
            if (predicate.test(documents[i])) {
                res[j] = documents[i];
                j++;
            }

        }
        return Arrays.copyOf(res, j);//обрезали массив,без элементов null
    }

    @Override
    public Document[] getDocBetweenDate(LocalDate dateFrom, LocalDate dateTo) {
        Document pattern = new Document(0, Integer.MIN_VALUE, null, null, dateFrom.atStartOfDay());
        //это ввели обьектную переменную шаблон
        int from = -Arrays.binarySearch(documents, 0, size, pattern, comparator) - 1;//находим индекс начального фото левый
        // край from = from>=0 ? from : -from-1;
        pattern = new Document(0, Integer.MAX_VALUE, null, null, LocalDateTime.of(dateTo, LocalTime.MAX));// находим
        // правый  край
        int to = -Arrays.binarySearch(documents, 0, size, pattern, comparator) - 1;
        // to = to >=0 ? to : -to-1;
        return Arrays.copyOfRange(documents, from, to);// диапазон, создаем новый массив с нужными документами

    }
    @Override
    public boolean updateDocument(int documentId, int folderId, String url) {
        //Document document; // нашли docum
        Document document = getDocFromFolder(documentId,folderId);
        if (document == null) {
            return false;
        }
        document.setUrl(url);
        return true;
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public void viewDocuments() {
        // for loop, print tasks
        for (int i = 0; i < size; i++) {
            System.out.println(documents[i]);
        }
        System.out.println("You have " + size + " documents.");
    }
}
