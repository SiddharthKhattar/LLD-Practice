import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;


// Interface for document elements
interface DocumentElement{
    public abstract String render(); 
}

// Concrete implementation for text elements
class TextElement implements DocumentElement{
    private String text;

    public TextElement(String text){
        this.text = text;
    }

    @Override
    public String render(){
        return text;
    }

}

// Concrete implementation for image elements
class ImageElement implements DocumentElement{
    private String imagePath;

    public ImageElement(String imagePath){
        this.imagePath = imagePath;
    }

    @Override
    public String render(){
        return "[Image: " + imagePath + "]";
    }
}

// NewLineElement represents a line break in the document
class NewLineElement implements DocumentElement{
    @Override
    public String render(){
        return "\n";
    }
}

// NewLineElement represents a tab space in the document
class TabSpaceElement implements DocumentElement{
    @Override
    public String render(){
        return "\t";
    }
}

// D 
class Document{
    private List<DocumentElement> documentElements = new ArrayList<>();

    public void addElement(DocumentElement element){
        documentElements.add(element);
    }

    // Renders the document by concatenating the render output of all elements
    public String render(){
        StringBuilder result = new StringBuilder();
        for(DocumentElement element: documentElements){
            result.append(element.render());
        }
        return result.toString();
    }
}


// Persistence Interface
interface Persistence{
    void save(String data);
}

class FileStorage implements Persistence{
    @Override
    public void save(String data){
        try {
            FileWriter outFile = new FileWriter("document.txt");
            outFile.write(data);
            outFile.close();
            System.out.println("Document saved to doc.txt");
        } catch (IOException e){
            System.out.println("Error! Unable to open file for writing");
        }
    }
}

// Placeholder DBStorage implementation
class DBStorage implements Persistence{
    @Override 
    public void save(String data){
        // Save to DB
    }
}

// DocumentEditor class managing client interactions
class DocumentEditor{
    private Document document;
    private Persistence storage;
    private String renderedDocument = "";

    public DocumentEditor(Document document, Persistence storage){
        this.document = document;
        this.storage = storage;
    }

    public void addText(String text){
        document.addElement(new TextElement(text));
    }

    public void addImage(String imagePath){
        document.addElement(new ImageElement(imagePath));
    }

    // adds a new line to the document
    public void addNewLine(){
        document.addElement(new NewLineElement());
    }

    public void addTabSpace(){
        document.addElement(new TabSpaceElement());
    }


    public String renderDocument(){
        if(renderedDocument.isEmpty()){
            renderedDocument = document.render();
        }

        return renderedDocument;
    }

    public void saveDocument(){
        storage.save(renderDocument());
    }
}



// client usage example
public class DocumentEditorClient{
    public static void main(String[] args){
        Document document = new Document();
        Persistence persistence = new FileStorage();

        DocumentEditor editor = new DocumentEditor(document, persistence);

        // Simulating a client using the editor with common text formatting features
        editor.addText("Hello World!");
        editor.addNewLine();
        
        editor.addText("Hello World 2");
        editor.addNewLine();
        editor.addTabSpace();

        editor.addText("Hello World III");
        editor.addImage("HelloWorldIV.jpg");

        // Render and display the final doc
        System.out.println(editor.renderDocument());

        editor.saveDocument();
    }
}