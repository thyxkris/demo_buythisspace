import helpers.ConfigHelper;
import helpers.ReportHelper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by makri on 20/09/2017.
 */
public class report {

   @Test
    public void gen() throws IOException {


        String reportFolder = ConfigHelper.getCurrentWorkingDir() + File.separator + ConfigHelper.getString("parallel.output.directory");
        ReportHelper reportHelper = new ReportHelper(reportFolder,".json");
        reportHelper.gen();
    }

}
