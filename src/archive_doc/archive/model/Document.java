package archive_doc.archive.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Document implements Comparable<Document>{
    //fields

    private int folderId;
    private int documentId;
    private String name;
    private String url;
    private LocalDateTime date;

    public Document(int folderId, int documentId, String name, String url, LocalDateTime date) {

        this.folderId = folderId;
        this.documentId = documentId;
        this.name = name;
        this.url = url;
        this.date = date;
    }

	public int getFolderId() {
		return folderId;
	}

	public int getDocumentId() {
		return documentId;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Document{" +
				"folderId=" + folderId +
				", documentId=" + documentId +
				", name='" + name + '\'' +
				", url='" + url + '\'' +
				", date=" + date +
				'}';
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		Document document = (Document) object;
		return folderId == document.folderId && documentId == document.documentId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(folderId, documentId);
	}

	@Override
    public int compareTo(Document o) {
        //надо определить как сортировать
        // обьекты в массиве по 2 полям
        int res = Integer.compare(folderId,o.folderId);
        return res != 0 ? res: Integer.compare(documentId,o.documentId);
        //if res != 0 return res else сравниваем compare
    }
}
