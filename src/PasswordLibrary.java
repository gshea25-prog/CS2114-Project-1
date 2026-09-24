import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PasswordLibrary {

    private final HashMap<String, Website> entries;

    public PasswordLibrary(){
        this.entries = new HashMap<>();
    }

    public boolean contains(String websiteName){
        return entries.containsKey(websiteName);
    }

    public void add(String websiteName, Website website){
        if(entries.containsKey(websiteName)){
            throw new IllegalArgumentException("The website " + websiteName + " is already present in the library");
        }
        entries.put(websiteName, website);
    }

    public Website remove(String websiteName){
        if(!entries.containsKey(websiteName)){
            throw new WebsiteNotFoundException("The website with the key " + websiteName + " was not found");
        }
        return entries.remove(websiteName);
    }

    public Website get(String websiteName){
        if(!entries.containsKey(websiteName)){
            throw new WebsiteNotFoundException("The website with the key " + websiteName + " was not found");
        }
        return entries.get(websiteName);
    }

    public void clear(){
        entries.clear();
    }

    public List<Website> getAllEntries(){
        return new ArrayList<>(entries.values());
    }

    public int size(){
        return entries.size();
    }

}
