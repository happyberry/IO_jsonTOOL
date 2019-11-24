# JSON TOOLS

Projekt na przedmiot Inżynieria Oprogramowania. Aplikacja dla programistów, którzy potrzebują przeformatować lub filtrować struktury danych zapisane w formacie JSON, a także porównać ze soba struktury. JSON Tools pozwala zarówno na zminifikowanie niezminifikowanej reprezentacji JSON, a także na operację odwrotną (z dodaniem wszelkich odstępów i nowych linii). Aplikacja będzie dostępna poprzez GUI, a także jako zdalne API, dzieki czemu można ją zintegrować z istniejącymi narzędziami.

URIS (prefix http://localhost:8080):

/dodaj - POST - dodawanie JSONa

/getAll - GET - wyświetla wrzuconego JSONa

/minify - GET - minifikacja

/unminifiy - GET - deminifikacja

/compare - PUT - porównywanie wrzuconego JSONa z tekstem dodanym w ciele zapytania

POST /dodaj w body wrzucacie jakiegos jsona i jego pozniej mozecie z GET wyswietlic ( /getAll ), minifikowac ( /minify ), deminifikowac ( /unminify ), oraz za pomoca PUT porownac ( /compare ) i w body wrzucacie drugi tekst, ktory porownuje z tym wrzuconym za pomoca POSTA

Zasady punktowania, link: https://docs.google.com/spreadsheets/d/e/2PACX-1vRh3VmprccDy5JC2hxggI1UmWwos2_ukytrvbvvzRT2BkgwskpF3wNIYTx4WXUhAopLKknACRZ65kWt/pubhtml

Planowanie sprintu, link z moodle: https://moodle.put.poznan.pl/pluginfile.php/138442/mod_resource/content/3/Planowanie_sprintu.pdf

![Build status](https://travis-ci.org/er713/IO_jsonTOOL.svg?branch=master)
