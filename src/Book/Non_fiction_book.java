package Book;

import Utils.Service;

import java.util.*;

public class Non_fiction_book extends Book{

    private String subject;
    private Map<Integer, String> references = new HashMap<>();

    @Override
    protected void method(){}

    public Non_fiction_book(){
        super();
    }

    public Non_fiction_book(String title, int page_count, Date release_date, List<Integer> authors_ids, String subject, Map<Integer, String> references) {
        super(title, page_count, release_date, authors_ids);
        this.subject = subject;
        this.references = references;
    }

    @Override
    public void read_book(Scanner scanner)
    {
        super.read_book(scanner);
        System.out.print("Subject: ");
        this.subject = scanner.nextLine();
        int references_count = Service.get_int(scanner, "How many references: ", "This is not a valid number!");
        for(int i = 1; i<= references_count; ++i)
        {
            System.out.print("Reference " + i + ": ");
            String reference = scanner.nextLine();
            this.references.put(i, reference);
        }
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Map<Integer, String> getReferences() {
        return references;
    }

    public void setReferences(Map<Integer, String> references) {
        this.references = references;
    }

    @Override
    public String toString() {
        return "Non_fiction_book{" +
                super.toString() + ", " +
                "subject='" + subject + '\'' +
                ", references=" + references +
                '}';
    }
}
