# Teaching-HEIGVD-RES-2015-Labo-01

### Introduction

The objective of this lab is to get familiar with the Java IO APIs and to interact with the file system. You will implement an application that performs the following tasks:

1. The user invokes the application on the **command line** and provides a numeric argument (*n*).
2. The application **uses a Web Service client** (which is provided to you) to fetch *n* **quotes** from the [iheartquotes](http://www.iheartquotes.com/) online service.
3. The application stores the content of each quote in a **text file** (utf-8), on the local filesystem. It uses the *tags* associated to each quote to create a hierarchical structure of directories.
4. The application then **traverses the local file system** (depth-first) and applies a processing to each visited quote file.
5. The processing consists of 1) converting all characters to their **uppercase** value and 2) adding a **line number** (followed by a tab character) at the beginning of each line. This is achieved by combining sub-classes of the `FilterWriter` class.

If your application is fully implemented you should have the following result on your machine:

#### A. When building the application


```
HEIGVD $ mvn clean install
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Build Order:
[INFO] 
[INFO] Lab01App-code
[INFO] Lab01App-tests
[INFO] Lab01App-build
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building Lab01App-code 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------

... (skipping some of the log output)

Results :

Tests run: 27, Failures: 0, Errors: 0, Skipped: 0

... (skipping some of the log output)

[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO] 
[INFO] Lab01App-code ..................................... SUCCESS [2.477s]
[INFO] Lab01App-tests .................................... SUCCESS [8.811s]
[INFO] Lab01App-build .................................... SUCCESS [0.004s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------

```

#### B. When running the application

```
HEIGVD $ java -jar Lab01App-code/target/Lab01App-code-1.0-SNAPSHOT-launcher.jar 15

INFO: codehappy
INFO: > codehappy
INFO: discworld
INFO: > discworld
INFO: > sourcery
INFO: fortune
INFO: > science
INFO: codehappy
INFO: > codehappy
INFO: > g_gordon_liddy_s
INFO: humorix_stories
INFO: > humorix_stories
INFO: codehappy
INFO: > codehappy
INFO: > ambrose_bierce
INFO: hitchhiker
INFO: > hitchhiker
INFO: codehappy
INFO: > codehappy
INFO: > fran_lebowitz
INFO: codehappy
INFO: > codehappy
INFO: codehappy
INFO: > codehappy
INFO: codehappy
INFO: > codehappy
INFO: fortune
INFO: > work
INFO: > the_realist
INFO: friends
INFO: > friends
INFO: codehappy
INFO: > codehappy
INFO: dune
INFO: > heretics_of_dune
INFO: ./workspace/quotes
./workspace/quotes/codehappy
./workspace/quotes/codehappy/quote-1.utf8
./workspace/quotes/codehappy/quote-10.utf8
./workspace/quotes/codehappy/quote-11.utf8
./workspace/quotes/codehappy/quote-14.utf8
./workspace/quotes/codehappy/quote-9.utf8
./workspace/quotes/codehappy/ambrose_bierce
./workspace/quotes/codehappy/ambrose_bierce/quote-6.utf8
./workspace/quotes/codehappy/fran_lebowitz
./workspace/quotes/codehappy/fran_lebowitz/quote-8.utf8
./workspace/quotes/codehappy/g_gordon_liddy_s
./workspace/quotes/codehappy/g_gordon_liddy_s/quote-4.utf8
./workspace/quotes/discworld
./workspace/quotes/discworld/sourcery
./workspace/quotes/discworld/sourcery/quote-2.utf8
./workspace/quotes/friends
./workspace/quotes/friends/quote-13.utf8
./workspace/quotes/heretics_of_dune
./workspace/quotes/heretics_of_dune/quote-15.utf8
./workspace/quotes/hitchhiker
./workspace/quotes/hitchhiker/quote-7.utf8
./workspace/quotes/humorix_stories
./workspace/quotes/humorix_stories/quote-5.utf8
./workspace/quotes/science
./workspace/quotes/science/quote-3.utf8
./workspace/quotes/work
./workspace/quotes/work/the_realist
./workspace/quotes/work/the_realist/quote-12.utf8
```

#### C. After running the application

```
HEIGVD $ find workspace/quotes

workspace/quotes
workspace/quotes/codehappy
workspace/quotes/codehappy/ambrose_bierce
workspace/quotes/codehappy/ambrose_bierce/quote-6.utf8
workspace/quotes/codehappy/ambrose_bierce/quote-6.utf8.out
workspace/quotes/codehappy/fran_lebowitz
workspace/quotes/codehappy/fran_lebowitz/quote-8.utf8
workspace/quotes/codehappy/fran_lebowitz/quote-8.utf8.out
workspace/quotes/codehappy/g_gordon_liddy_s
workspace/quotes/codehappy/g_gordon_liddy_s/quote-4.utf8
workspace/quotes/codehappy/g_gordon_liddy_s/quote-4.utf8.out
workspace/quotes/codehappy/quote-1.utf8
workspace/quotes/codehappy/quote-1.utf8.out
workspace/quotes/codehappy/quote-10.utf8
workspace/quotes/codehappy/quote-10.utf8.out
workspace/quotes/codehappy/quote-11.utf8
workspace/quotes/codehappy/quote-11.utf8.out
workspace/quotes/codehappy/quote-14.utf8
workspace/quotes/codehappy/quote-14.utf8.out
workspace/quotes/codehappy/quote-9.utf8
workspace/quotes/codehappy/quote-9.utf8.out
workspace/quotes/discworld
workspace/quotes/discworld/sourcery
workspace/quotes/discworld/sourcery/quote-2.utf8
workspace/quotes/discworld/sourcery/quote-2.utf8.out
workspace/quotes/friends
workspace/quotes/friends/quote-13.utf8
workspace/quotes/friends/quote-13.utf8.out
workspace/quotes/heretics_of_dune
workspace/quotes/heretics_of_dune/quote-15.utf8
workspace/quotes/heretics_of_dune/quote-15.utf8.out
workspace/quotes/hitchhiker
workspace/quotes/hitchhiker/quote-7.utf8
workspace/quotes/hitchhiker/quote-7.utf8.out
workspace/quotes/humorix_stories
workspace/quotes/humorix_stories/quote-5.utf8
workspace/quotes/humorix_stories/quote-5.utf8.out
workspace/quotes/science
workspace/quotes/science/quote-3.utf8
workspace/quotes/science/quote-3.utf8.out
workspace/quotes/work
workspace/quotes/work/the_realist
workspace/quotes/work/the_realist/quote-12.utf8
workspace/quotes/work/the_realist/quote-12.utf8.out
```

```
HEIGVD $ cat workspace/quotes/codehappy/g_gordon_liddy_s/quote-4.utf8

When alerted to an intrusion by tinkling glass or otherwise, 1) Calm
yourself 2) Identify the intruder 3) If hostile, kill him.

Step number 3 is of particular importance.  If you leave the guy alive
out of misguided softheartedness, he will repay your generosity of spirit
by suing you for causing his subsequent paraplegia and seek to force you
to support him for the rest of his rotten life.  In court he will plead
that he was depressed because society had failed him, and that he was
looking for Mother Teresa for comfort and to offer his services to the
poor.  In that lawsuit, you will lose.  If, on the other hand, you kill
him, the most that you can expect is that a relative will bring a wrongful
death action. You will have two advantages: first, there be only your
story; forget Mother Teresa.  Second, even if you lose, how much could
the bum's life be worth anyway?  A Lot less than 50 years worth of
paralysis.  Don't play George Bush and Saddam Hussein.  Finish the job.
	-- G. Gordon Liddy's "Forbes" column on personal security
```

```
HEIGVD $ cat workspace/quotes/codehappy/g_gordon_liddy_s/quote-4.utf8.out 

1	WHEN ALERTED TO AN INTRUSION BY TINKLING GLASS OR OTHERWISE, 1) CALM
2	YOURSELF 2) IDENTIFY THE INTRUDER 3) IF HOSTILE, KILL HIM.
3	
4	STEP NUMBER 3 IS OF PARTICULAR IMPORTANCE.  IF YOU LEAVE THE GUY ALIVE
5	OUT OF MISGUIDED SOFTHEARTEDNESS, HE WILL REPAY YOUR GENEROSITY OF SPIRIT
6	BY SUING YOU FOR CAUSING HIS SUBSEQUENT PARAPLEGIA AND SEEK TO FORCE YOU
7	TO SUPPORT HIM FOR THE REST OF HIS ROTTEN LIFE.  IN COURT HE WILL PLEAD
8	THAT HE WAS DEPRESSED BECAUSE SOCIETY HAD FAILED HIM, AND THAT HE WAS
9	LOOKING FOR MOTHER TERESA FOR COMFORT AND TO OFFER HIS SERVICES TO THE
10	POOR.  IN THAT LAWSUIT, YOU WILL LOSE.  IF, ON THE OTHER HAND, YOU KILL
11	HIM, THE MOST THAT YOU CAN EXPECT IS THAT A RELATIVE WILL BRING A WRONGFUL
12	DEATH ACTION. YOU WILL HAVE TWO ADVANTAGES: FIRST, THERE BE ONLY YOUR
13	STORY; FORGET MOTHER TERESA.  SECOND, EVEN IF YOU LOSE, HOW MUCH COULD
14	THE BUM'S LIFE BE WORTH ANYWAY?  A LOT LESS THAN 50 YEARS WORTH OF
15	PARALYSIS.  DON'T PLAY GEORGE BUSH AND SADDAM HUSSEIN.  FINISH THE JOB.
16		-- G. GORDON LIDDY'S "FORBES" COLUMN ON PERSONAL SECURITY
```


### Tasks

Here is the proposed list of tasks to achieve the objectives:

1. Start by forking and cloning this repo (**mandatory!!**).
2. From the main project, do a `mvn clean install` and notice that the tests fail.
3. Spend some time to explore the package structure (and watch [this](https://www.youtube.com/watch?v=v_ZpVgf0lGc&list=PLfKkysTy70Qb_mfkkqa5OUMqsOPNEYZIa&index=5) Youtube video).
4. Examine the automated tests in the test project and do a step-by-step implementation, until all tests are green. Here is a proposed order for fixing the broken tests:
   - `ApplicationTest.java`
   - `UtilsTest.java`
   - `UpperCaseFilterWriterTest.java`
   - `DFSFileExplorerTest.java`
   - `FileTransformerTest.java`
   - `FileNumberingFilterWriterTest.java`
   - `CompleteFileTransformerTest.java`
5. Each time that you fix a failing test, `commit` your work (and use a **meaningful message**)
6. When all the tests are green, invoke the application (`java -jar`) from the top-level directory (the directory that contains the `.git` hidden folder). Inspect the content of the `workspace/quotes` directory and check that the output files are correct.
7. When you are done, make sure that you have committed all your work and push your commits.
8. To make a final validation, move to a fresh directory. Clone your fork. Do a `mvn clean install` and make sure that the tests are still green and that the app still produces the correct output.
9. Submit a pull request.