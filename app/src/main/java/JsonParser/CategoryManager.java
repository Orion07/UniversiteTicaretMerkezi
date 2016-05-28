package JsonParser;

/**
 * Created by Hakan on 15.11.2015.
 */
public class CategoryManager {
    public int cateID;
    public String cateName;
    public int iconID;

    public CategoryManager(int cateID, String cateName, int iconID) {
        this.cateID = cateID;
        this.cateName = cateName;
        this.iconID = iconID;
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

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }





}