# MyShelfie: Ronchieri, Proverbio, Tagab, Pazienza
#
# Implementation: 
   - Complete Rules
   - TUI
   - RMI
   - Advanced Functionality: Resilience to disconnections

# How to run the application:
    To be able to play the game you must have ANSI standard 
    enabled on your computer in order to be able to see colors in the TUI
    otherwise it is going to print a string with numbers and other char, this will
    allow you to play by using shelf's position & slot's names.
    This will ruin game experince!

    We made two different JAR files, one for the server and
    the other one for the client.

    To launch them you need to use your machine's command line
    and type:
    java -jar +absolute path/MyShelfieClient.jar 
    You can also reach the directory where is saved the JAR file 
    that you want to run and then type:
    java -jar +MyShelfieServer.jar 

    (you can switch Sever.jar to Client.jar)

    If you decide to run server app you need to insert your
    IP address (one where you will be reachable), server will run by default on port 1068.

    If you decide to run client app you need to insert server's IP
    address that you want to get connected to.

# Disclaimer 
    Crash will be detected in several ways: 

    - While On game: by a 30s ping to find any RemoteException
    - At every end turn 
    - While in PreGame: a client crash will be notified only when a new client is going to 
        try to sing to the lobby 

    In order to avoid a lock, it MUST need to get subscribed only one First player! 
        Since this version is not allowing multiple istance game.

