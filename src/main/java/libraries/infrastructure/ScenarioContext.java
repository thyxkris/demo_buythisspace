package libraries.infrastructure;

import infrastructure.KBaseContext;
import pages.BaseModel;
import pages.BasePageModel;

/**
 * Created by Kris Ma on 21/05/2017.
 */
public class ScenarioContext extends KBaseContext {

    //here we have to override the get and setCurrentPage()
    public <T extends BasePageModel> T getCurrentPage() {
        return (T) this.get("currentPage");
    }

    public <T extends BaseModel> T setCurrentPage(T currentPage) {
        this.put("currentPage", currentPage);
        return (T) this.get("currentPage");
    }


}
