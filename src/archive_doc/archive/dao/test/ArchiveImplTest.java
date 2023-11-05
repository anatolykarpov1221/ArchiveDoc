package archive_doc.archive.dao.test;

import archive_doc.archive.dao.Archive;
import archive_doc.archive.dao.ArchiveImpl;
import archive_doc.archive.model.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArchiveImplTest {
    Archive archive;
    Document[] doc;
    LocalDateTime now = LocalDateTime.now();



    @BeforeEach
    void setUp() {
        archive = new ArchiveImpl(7);

        doc = new Document[6];

        doc[0] = new Document(1,1,"t1","url1",now.minusDays(2));
        doc[1] = new Document(1,2,"t2","url2",now.minusDays(3));
        doc[2] = new Document(1,3,"t3","url3",now.minusDays(5));
        doc[3] = new Document(2,4,"t1","url4",now.minusDays(7));
        doc[4] = new Document(2,5,"t2","url5",now.minusDays(7));
        doc[5] = new Document(2,6,"t3","url6",now.minusDays(7));
                     //не забыть положить эти wikiliks в альбои методом addDocument
        for (int i = 0; i <doc.length; i++) {
            archive.addDocument(doc[i]);

        }

    }

    @Test
    void addDocument() {
        //нельзя добавить 0
        assertFalse(archive.addDocument(null));
        //уже есть занято
        assertFalse(archive.addDocument(doc[1]));
        //возможность добавления
       Document document = new Document(3,1,"t7","url",now);
        assertTrue(archive.addDocument(document));


        //size проверить
        assertEquals(7,archive.size());
        //capacity ne bolee
       document = new Document(3,1,"t7","url",now);
        assertFalse(archive.addDocument(document));
    }



    @Test
    void updateDocument() {
        assertTrue(archive.updateDocument(1, 1, "newURL"));
        assertEquals("newURL",archive.getDocFromFolder(1,1).getUrl());//проверили url

    }

    @Test
    void getPhotoFromAlbum() {
        assertEquals(doc[0],archive.getDocFromFolder(1,1));//проверка нахождения фото
        assertNull(archive.getDocFromFolder(1,3));// ищем несуществующее
    }

    @Test
    void getAllPhotoFromAlbum() {
        // массив фото возвращает
        Document[] expected = {doc[3],doc[4],doc[5]};
        Document[] actual = archive.getAllDocFromFolder(2);//выбрали Folder Id 2
        Arrays.sort(actual);// сортируем
        assertArrayEquals(expected,actual);
    }

    @Test
    void getPhotoBetweenDate() {
        LocalDate ld = now.toLocalDate(); // оставляем только дату
        Document[] actual = archive.getDocBetweenDate(ld.minusDays(6), ld.minusDays(1));
        Arrays.sort(actual);
        Document[] expected = {doc[0],doc[1],doc[2]};
        assertArrayEquals(expected,actual);
    }

    @Test
    void size() {

        assertEquals(6,archive.size());
    }
}