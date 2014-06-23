package gr.planetek.esa.saps.es.es_loader;

import gr.planetek.esa.saps.es.es_loader.util.ProgressBar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.WordUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Hello world!
 *
 */
public class App {

    private File skeletonFile = new File("datasets/saps.eloader.skeleton");
    private File outFile = new File("datasets/saps.random.eloader");

    private String[] publications = { "Astronomy and Science", "Journal of Deep space", "Galaxian",
            "Science for all", "Nature" };
    private int[] publicationsWeight = { 1, 2, 2, 1, 3 };
    private List<String> publicationsDistr;

    // http://listofrandomnames.com/
    private String[] authors = { "Caprice Coppin", "Nichol Nath", "Lynelle Lydon",
            "Leatrice Larson", "Mellissa Mastro", "Rocio Rockwell", "Carolynn Chapin",
            "Angela Alford", "Evon Ehrhart", "Ma Mcgarrah", "Patrice Pendelton", "Aletha Aubert",
            "Shemeka Suddeth", "Jacquiline Jude", "Neville Nicol", "Estell Eaglin",
            "Nadia Nierman", "Kimberli Kutz", "Michiko Meyerson", "Jong Jehle",
            "Demetria Dezzutti", "Fredric Fiorita", "Marilu Massingale", "Melania Mancha",
            "Merna Meade", "Allison Aubry", "Rochelle Rives", "Glynis Graziani", "Page Peavy",
            "Jenna Jo", "Renaldo Rines", "Macie Mosier", "Brenda Bondi", "Eliseo Eversole",
            "Patricia Poage", "Shanna Soluri", "Mae Mui", "Cory Chard", "Elijah Easterly",
            "Tana Tillery", "Hanna Hodes", "Guillermina Giannone", "Myrtie Mirabal",
            "Brittanie Biehl", "Margurite Mom", "Renna Ripley", "Jeannette Jun",
            "Charlott Canizales", "Edelmira Eakins", "Stefany Streets" };

    private String titles = "Amphibious communication and red-black trees have garnered limited interest from both cryptographers and mathematicians in the last several years. Even though previous solutions to this grand challenge are numerous, none have taken the wireless solution we propose here. The basic tenet of this approach is the visualization of digital-to-analog converters. The visualization of semaphores would profoundly improve the study of interrupts. Sicca, our new application for Bayesian epistemologies, is the solution to all of these challenges. The usual methods for the simulation of write-back caches do not apply in this area. Existing large-scale and wireless algorithms use the construction of the transistor to allow perfect technology. We emphasize that our methodology is copied from the analysis of 8 bit architectures. Existing Bayesian and omniscient methodologies use model checking to provide local-area networks. The drawback of this type of method, however, is that Byzantine fault tolerance can be made trainable, metamorphic, and empathic. The rest of this paper is organized as follows. Primarily, we motivate the need for red-black trees [25]. On a similar note, we verify the development of Web services. Along these same lines, we disprove the practical unification of the lookaside buffer and XML. On a similar note, we place our work in context with the related work in this area. Ultimately, we conclude.";
    private List<String> titleRandomWords;

    private String[] missions = { "XMM", "Herschel", "Cassini/Hyugens", "Exosat", "Integral",
            "SOHO", "Cluster", "ISO" };

    private String[] instruments = { "Aloan", "Dreras", "Eroazah", "Aror", "Jak", "Caiv",
            "Gaikler", "Orouzar", "Dak", "Xillim", "Fluly", "Ka", "Trukas", "Jounda", "Cechar",
            "Erees", "Voallra", "Fushn", "Inith", "Meroa" };
    private Random random;
    private ArrayList authorsShuffled;

    public App() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {

        App dummy = new App();
        if (args == null || args.length == 0) {
            System.out.println("You have to specify the number of random samples to generate.");
            System.exit(-1);
        }
        dummy.run(Integer.parseInt(args[0]));

    }

    private void run(int publications) {
        initArrays();
        fixFiles();

        ProgressBar bar = new ProgressBar(publications);

        for (int i = 0; i < publications; i++)
            try {
                bar.iterate();
                FileUtils.writeStringToFile(outFile, generatePublicationObject(i + 1), true);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        System.out.println(String.format("Generated %d random samples in file: %s", publications,
                outFile.getAbsolutePath()));
    }

    private void fixFiles() {
        try {
            FileUtils.deleteQuietly(outFile);
            FileUtils.copyFile(skeletonFile, outFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String generatePublicationObject(int id) {
        JSONObject pub = new JSONObject();

        pub.put("id", id);
        pub.put("title", generateTitle());
        pub.put("authors", generateAuthors());
        pub.put("doi", generateDoi());

        int obsNum = random.nextInt(5);

        JSONArray observations = new JSONArray();
        for (int i = 0; i < obsNum; i++) {
            observations.add(generateObservationObject());
        }

        pub.put("observation", observations);

        pub.put("year", generateYear());
        pub.put("journal", generateJournal());

        return pub.toString() + "\n";

    }

    private Object generateAuthors() {
        int authors = random.nextInt(5) + 3;
        int startFrom = random.nextInt(authorsShuffled.size() - authors - 1);

        JSONArray auths = new JSONArray();

        for (int i = 0; i < authors; i++) {
            auths.add(authorsShuffled.get(startFrom + i));
        }
        return auths;

    }

    private Object generateYear() {
        return random.nextInt(34) + 1980;
    }

    private JSONObject generateObservationObject() {

        JSONObject obj = new JSONObject();
        obj.put("id", generateObservationId());
        obj.put("mission", generateMission());
        obj.put("instrument", generateInstrument());

        return obj;
    }

    private void initArrays() {

        // titles
        titleRandomWords = new ArrayList(Arrays.asList(titles.split(" ")));
        Collections.shuffle(titleRandomWords);

        authorsShuffled = new ArrayList(Arrays.asList(authors));
        Collections.shuffle(authorsShuffled);

        // publications
        publicationsDistr = new ArrayList<String>();

        for (int i = 0; i < publicationsWeight.length; i++) {
            for (int j = 0; j < publicationsWeight[i]; j++) {
                publicationsDistr.add(publications[i]);
            }
        }

        random = new Random();
    }

    private String generateDoi() {
        UUID doi = UUID.randomUUID();
        return doi.toString().substring(19);
    }

    private String generateObservationId() {
        UUID id = UUID.randomUUID();
        return id.toString().substring(0, 8);
    }

    private String generateTitle() {
        int words = random.nextInt(15) + 5;
        int startFrom = random.nextInt(titleRandomWords.size() - words - 1);

        StringBuffer title = new StringBuffer();

        for (int i = 0; i < words; i++) {
            title.append(titleRandomWords.get(startFrom + i) + " ");
        }
        return WordUtils.capitalize(title.toString().trim());

    }

    private String generateInstrument() {
        return instruments[random.nextInt(instruments.length)];
    }

    private String generateMission() {
        return missions[random.nextInt(missions.length)];
    }

    private String generateJournal() {
        return publicationsDistr.get(random.nextInt(publicationsDistr.size()));
    }
}
