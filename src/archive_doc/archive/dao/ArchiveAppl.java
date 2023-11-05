package archive_doc.archive.dao;

import archive_doc.archive.model.Document;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Scanner;



		public class ArchiveAppl {
			public static void main(String[] args) {
				System.out.println("Welcome to Archive Application!");
				ArchiveImpl archive = new ArchiveImpl(10);

				Scanner scanner = new Scanner(System.in);
				int id = 0;

				while (true) {
					System.out.println("Input your choice: 1 - Add Document, 2 - View all, 3 - Quantity, 4 - Exit");
					int choice = scanner.nextInt();

					switch (choice) {
						case 1: {
							scanner.nextLine();
							System.out.println("Enter document details: ");
							System.out.print("Document ID: ");
							int docId = scanner.nextInt();
							System.out.print("Folder ID: ");
							int folderId = scanner.nextInt();
							System.out.print("Document Name: ");
							scanner.nextLine();
							String name = scanner.nextLine();
							System.out.print("URL: ");
							String url = scanner.nextLine();

							Document document = new Document(folderId, docId, name, url, LocalDateTime.now());
							archive.addDocument(document);
							break;
						}
						case 2: {
							System.out.println("Your documents:");
							archive.viewDocuments();
							break;
						}
						case 3: {
							System.out.println("Quantity: " + archive.size());
							break;
						}
						case 4: {
							System.out.println("Exiting the application. Goodbye!");
							System.exit(0);
						}
						default: {
							System.out.println("Wrong input. Please enter a valid option.");
						}
					}
				}
			}
		}








