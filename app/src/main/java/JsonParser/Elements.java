package JsonParser;

import java.util.ArrayList;
import java.util.HashMap;

import Functions.Basic;

/**
 * Created by Orion on 20.11.2015.
 */
public class Elements
{
    public ArrayList<ElementManager> elementsList = new ArrayList<ElementManager>();
    public  Elements(int index)
    {
        switch (index)
        {
            case 1:
                /*elementsList.add(new ElementManager("resim","Elektronik","antalya","100"));
                elementsList.add(new ElementManager("resim","Elektronik2","antalya","200"));*/
                break;
            case 2:
                elementsList.add(new ElementManager("resim","Kirtasiye","antalya","300"));
                elementsList.add(new ElementManager("resim","Kirtasiye2","antalya","400"));
                break;
            default:
                elementsList.add(new ElementManager("resim","test","antalya","350"));
                elementsList.add(new ElementManager("resim","test2","antalya","450"));
                elementsList.add(new ElementManager("resim","test3","antalya","550"));
                elementsList.add(new ElementManager("resim","test4","antalya","650"));
                break;

        }

    }
    public ArrayList<ElementManager> getElementsList() {
        return elementsList;
    }
}
