package JsonParser;

/**
 * Created by Hakan on 15.11.2015.
 */
public class CategoryManager {
    public int cateID;
    public String cateName;
    public int parentID;

    public CategoryManager(int cateID, String cateName, int parentID) {
        this.cateID = cateID;
        this.cateName = cateName;
        this.parentID = parentID;
    }
    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }





}
