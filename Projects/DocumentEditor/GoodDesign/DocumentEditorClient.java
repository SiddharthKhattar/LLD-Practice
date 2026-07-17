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
}