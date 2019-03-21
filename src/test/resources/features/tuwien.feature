# language: de
# encoding: utf-8
  @ignore
Funktionalität: [TUW [tuwien]

  Grundlage:
    Angenommen die Startseite ist geladen


  Szenario: [TUW-01 [tuwien] Happy Test
    Dann sollte das TU-Logo angezeigt werden

  Szenario: [TUW-02 [tuwien] Suche
    Angenommen "Boris" befindet sich im Benutzerverzeichnis
    Wenn ich nach "Boris" suche
    Dann sollte ein Link zu "Boris" angezeigt werden


  Szenario: [TUW-03 [tuwien] Telefonnummer finden
    Angenommen im Telefonverzeichnis existiert "Boris"
    Wenn im Telefonverzeichnis nach "Boris" gesucht wird
    Dann sollte die Telefonnummer angezeigt werden


