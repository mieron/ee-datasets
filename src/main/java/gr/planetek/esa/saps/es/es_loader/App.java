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
            "Science for all", "Nature", "Star Catalogue", "Hacette" };
    private int[] publicationsWeight = { 1, 2, 2, 1, 3, 2 };
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
            "Charlott Canizales", "Edelmira Eakins", "Stefany Streets", "Carmelia Curtsinger",
            "Brigette Boros", "Lakisha Loyola", "Marcela Merchant", "Jacquelynn Janz", "Rana Rotz",
            "Shaniqua Sokol", "Sammy Staff", "Lise Lankford", "Bradford Bozek", "Jacqui Jepson",
            "Dell David", "Cassey Costigan", "Thad Thill", "Kenny Kicklighter",
            "Micaela Marchetti", "Twila Tan", "Margene Moon", "Wilda Westerberg",
            "Charlyn Cortese", "Talisha Tunison", "Trinh Talbott", "Korey Kocian",
            "Rosalba Rahimi", "Staci Snapp", "Richard Ryland", "Loretta Lichtenstein",
            "Marguerite Montesano", "Kendall Klinger", "Maudie Maass", "Shalon Sylva",
            "Rebecka Rode", "Lakenya Lasalle", "Carli Carty", "Neal Nalley", "Carmelita Callis",
            "Nicolette Neary", "Rudolf Recore", "Charolette Cobble", "Ambrose Andreasen",
            "Gertha Glassford", "Stefania Scruggs", "Cheryle Courville", "Adriana Ard",
            "Karry Kazmierczak", "Tessie Tarin", "Maile Mees", "Juliette Jeansonne", "Inger Ishii",
            "Eleonor Ehmann" };

    private String titles = "Amphibious communication and red-black trees have garnered limited interest from both cryptographers and mathematicians in the last several years. Even though previous solutions to this grand challenge are numerous, none have taken the wireless solution we propose here. The basic tenet of this approach is the visualization of digital-to-analog converters. The visualization of semaphores would profoundly improve the study of interrupts. Sicca, our new application for Bayesian epistemologies, is the solution to all of these challenges. The usual methods for the simulation of write-back caches do not apply in this area. Existing large-scale and wireless algorithms use the construction of the transistor to allow perfect technology. We emphasize that our methodology is copied from the analysis of 8 bit architectures. Existing Bayesian and omniscient methodologies use model checking to provide local-area networks. The drawback of this type of method, however, is that Byzantine fault tolerance can be made trainable, metamorphic, and empathic. The rest of this paper is organized as follows. Primarily, we motivate the need for red-black trees [25]. On a similar note, we verify the development of Web services. Along these same lines, we disprove the practical unification of the lookaside buffer and XML. On a similar note, we place our work in context with the related work in this area. Ultimately, we conclude.";

    private String publicationAbstract = " XMM–Newton X-ray spectra of the hard state black hole X-ray binaries (BHXRBs) SWIFT J1753.5−0127 and GX339−4 show evidence for accretion disc blackbody emission, in addition to hard power laws. The soft and hard band power spectral densities (PSDs) of these sources demonstrate variability over a wide range of time-scales. However, on timescales of tens of seconds, corresponding to the putative low-frequency Lorentzian in the PSD, there is additional power in the soft band. To interpret this behaviour, we introduce a new spectral analysis technique, the ‘covariance spectrum’, to disentangle the contribution of the X-ray spectral components to variations on different time-scales. We use this technique to show that the disc blackbody component varies on all time-scales, but varies more, relative to the power law, on longer time-scales. This behaviour explains the additional long-term variability seen in the soft band. Comparison of the blackbody and iron line normalizations seen in the covariance spectra in GX339−4 implies that the short-term blackbody variations are driven by thermal reprocessing of the power-law continuum absorbed by the disc. However, since the amplitude of variable reflection is the same on long and short time-scales, we rule out reprocessing as the cause of the enhanced disc variability on long time-scales. Therefore,  we conclude that the long time-scale blackbody variations are caused by instabilities in the disc itself, in contrast to the stable discs seen in BHXRB soft states. Our results provide the first observational evidence that the low-frequency Lorentzian feature present in the PSD is produced by the accretion disc"
            + "A high signal-to-noise 52–90 μm spectrum is presented for the central part of the Sagittarius B2 complex. The data were obtained with the LongWavelength Spectrometer on board the Infrared Space Observatory (ISO). The [O I] 63 μm line is detected in absorption even at the grating spectral resolution of 0.29 μm. A lower limit for the column density of atomic oxygen of the order of 1019 cm−2 is derived. This implies that more than 40% of the interstellar oxygen must be in atomic form along the line of sight toward the Sgr B2 molecular cloud."
            + "We construct evolutionary models of the populations of active galactic nuclei (AGNs) and supermassive black holes, in which the black hole mass function grows at the rate implied by the observed luminosity function, given assumptions about the radiative efficiency and the luminosity in Eddington units. We draw on a variety of recent X-ray and optical measurements to estimate the bolometric AGN luminosity function and compare to X-ray background data and the independent estimate of Hopkins et al. to assess remaining systematic uncertainties. The integrated AGN emissivity closely tracks the cosmic star-formation history, suggesting that star formation and black hole growth are closely linked at all redshifts. We discuss observational uncertainties in the local black hole mass function, which remain substantial, with estimates of the integrated black hole mass density ρ• spanning the range 3–5.5 × 105M Mpc−3. We find good agreement with estimates of the local mass function for a reference model where all active black holes have a fixed efficiency  = 0.065 and Lbol/LEdd ≈ 0.4 (shifting to  = 0.09, Lbol/LEdd ≈ 0.9 for the Hopkins et al. luminosity function). In our reference model, the duty cycle of 109M black holes declines from 0.07 at z = 3 to 0.004 at z = 1 and 10−4 at z = 0. The decline is shallower for less massive black holes, a signature of “downsizing” evolution in which more massive black holes build their mass earlier. The predicted duty cycles andAGNclustering bias in this model are in reasonable accord with observational estimates.";

    private List<String> titleRandomWords;

    private List<String> publicationAbstractRandomWords;

    private String[] missions = { "XMM", "Herschel", "Cassini/Hyugens", "Exosat", "Integral",
            "SOHO", "Cluster", "ISO" };

    private String[] instruments = { "Aloan", "Dreras", "Eroazah", "Aror", "Jak", "Caiv",
            "Gaikler", "Orouzar", "Dak", "Xillim", "Fluly", "Ka", "Trukas", "Jounda", "Cechar",
            "Erees", "Voallra", "Fushn", "Inith", "Meroa" };

    private String[] filters = { "Medium", "Thin 1", "UNIDEF", "Blue", "Red", "Thick",
            "Ultra-wide", "Long", "Short", "Smooth", "Crooked", "Narrow" };

    private String[] targets = { "Abell 2690", "RXJ2359.5-3211", "GRB 101225A", "CTB1SW", "m51",
            "Cryocover-7", "CVZ-North-1", "NGC 7027", "Saturn", "Uranus20090624", "Sirius",
            "Orion", "m34", "m36", "Venus", "Mars", "Cygnus-X", "Albaderan", "Tattoinne" };

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
        pub.put("publicationAbstract", generateAbstract());
        pub.put("authors", generateAuthors());
        pub.put("doi", generateDoi());

        int obsNum = random.nextInt(5);

        // commenting the following line you will get observations in multiple different missions
        // BUT, that's not how our system works. All Obs in a Pub belong to a single Mission.
        String mission = generateMission();
        pub.put("mission", mission);

        JSONArray observations = new JSONArray();
        for (int i = 0; i < obsNum; i++) {
            observations.add(generateObservationObject(mission));
        }

        pub.put("observations", observations);
        pub.put("observationsCount", obsNum);

        pub.put("publishedYear", generateYear());
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

    private JSONObject generateObservationObject(String mission) {

        JSONObject obj = new JSONObject();
        obj.put("id", generateObservationId());
        obj.put("obsId", generateObservationId());
        obj.put("mission", mission != null ? mission : generateMission());

        JSONArray obsMetadata = new JSONArray();

        int metadataCount = random.nextInt(3);
        for (int i = 0; i < metadataCount; i++) {
            obsMetadata.add(generateObsMetadataObject(mission));
        }

        obj.put("obsMetadata", obsMetadata);
        return obj;
    }

    private JSONObject generateObsMetadataObject(String mission) {

        JSONObject obj = new JSONObject();
        obj.put("id", generateObservationId());
        obj.put("obsId", generateObservationId());
        obj.put("instrument", generateInstrument());
        obj.put("filter", generateFilter());
        obj.put("targetName", generateTarget());

        return obj;
    }

    private void initArrays() {

        // titles
        titleRandomWords = new ArrayList(Arrays.asList(titles.split(" ")));
        Collections.shuffle(titleRandomWords);

        // abstracts
        publicationAbstractRandomWords = new ArrayList(
                Arrays.asList(publicationAbstract.split(" ")));
        Collections.shuffle(publicationAbstractRandomWords);

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

    private String generateAbstract() {
        int words = random.nextInt(200) + 5;
        int startFrom = random.nextInt(publicationAbstractRandomWords.size() - words - 1);

        StringBuffer title = new StringBuffer();

        for (int i = 0; i < words; i++) {
            title.append(publicationAbstractRandomWords.get(startFrom + i) + " ");
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

    private String generateTarget() {
        return targets[random.nextInt(targets.length)];
    }

    private String generateFilter() {
        return filters[random.nextInt(filters.length)];
    }
}
