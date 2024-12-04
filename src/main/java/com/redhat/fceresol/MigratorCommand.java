package com.redhat.fceresol;

import com.redhat.fceresol.files.CSVHandler;
import com.redhat.fceresol.config.MigratorConfig;
import com.redhat.fceresol.exceptions.LoadException;
import com.redhat.fceresol.threescale.ConfigLoader;
import com.redhat.fceresol.threescale.ObjectMerger;
import com.redhat.fceresol.threescale.api.clients.DestThreescaleClient;
import com.redhat.fceresol.threescale.api.clients.SourceThreescaleClient;
import io.quarkus.logging.Log;
import io.quarkus.picocli.runtime.annotations.TopCommand;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import picocli.CommandLine;
import picocli.CommandLine.Command;

//@QuarkusMain
@TopCommand
@Command(mixinStandardHelpOptions = true, subcommands = {MigrateCommand.class, CreateCsvCommand.class})
public class MigratorCommand{} /*implements QuarkusApplication {

    public static void main(String... args) {
        Quarkus.run(MigratorCommand.class, args);
    }
     @Inject
    CommandLine.IFactory factory; 


    @Override
    public int run(String... args) throws Exception {
        return new CommandLine(this, factory).execute(args);
    }
    
}*/

@Command(name = "migrateData", description = "migrates the data listed inside CSV")
class MigrateCommand implements Runnable {

    @Inject
    MigratorConfig config;

    @Inject
    @RestClient
    DestThreescaleClient destClient;
    
    @Inject
    @RestClient
    SourceThreescaleClient sourceClient;
    
    
    @CommandLine.Option(names = {"-i", "--input"}, description = "Input CSV file")
    public File inputCSV;

    @Override
    public void run() {
        CSVHandler csv = new CSVHandler(inputCSV);
        Map<Integer,String> csvMapping;
        try {
            csvMapping = csv.readCSV();
            ObjectMerger merger = new ObjectMerger(sourceClient, destClient, csvMapping, config);
            Log.info("loading configs....");
            merger.loadAll();
            Log.info("creating missing accounts....");
            merger.createMissingAccounts();
            Log.info("creating missing backends....");
            merger.createMissingBackends();
            Log.info("creating missing services....");
            merger.createMissingServices();
            
        } catch (IOException | LoadException ex) {
            Log.error(ex);
            
        }
        

        

    }
}

@Command(name = "createCSV", description = "Creates CSV for selecting data to be migrated")
class CreateCsvCommand implements Runnable {

    @Inject
    MigratorConfig config;

    @Inject
    @RestClient
    SourceThreescaleClient sourceClient;
    @CommandLine.Option(names = {"-o", "--output"}, description = "Output file", required = true)
    public File outputCSV;

    @Override
    public void run() {

        ConfigLoader loader = new ConfigLoader(sourceClient, config, config.exportToken());

        try {
            loader.loadConfigurations();

            CSVHandler csv = new CSVHandler(outputCSV);
            csv.writeOutput(loader.getServices());
            //System.out.println(services.getStatus());
            // System.out.println(services.getStatusInfo().getReasonPhrase());
            //Services entity = services.getEntity();
            /*try {
            loader.loadConfigurations();
            } catch (LoadException ex) {
            Logger.getLogger(GreetingCommand.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        } catch (LoadException ex) {

        } catch (IOException ex) {

        }

    }

}
