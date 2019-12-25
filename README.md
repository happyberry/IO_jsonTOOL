# JSON TOOLS

Projekt na przedmiot Inżynieria Oprogramowania. Aplikacja dla programistów, którzy potrzebują przeformatować lub filtrować struktury danych zapisane w formacie JSON, a także porównać ze soba struktury. JSON Tools pozwala zarówno na zminifikowanie niezminifikowanej reprezentacji JSON, a także na operację odwrotną (z dodaniem wszelkich odstępów i nowych linii). Aplikacja będzie dostępna poprzez GUI, a także jako zdalne API, dzieki czemu można ją zintegrować z istniejącymi narzędziami.

URIS (prefix http://localhost:8080):

/minify - POST - minifikacja JSONa wrzuconego jako ciało zapytania

/unminify - POST - deminifikacja JSONa wrzuconego jako ciało zapytania

/compare - POST - porównywanie dwóch plików tekstowych wrzuconych jako ciało zapytania

/checkSizeDifference - POST - szacowanie zysku z minifikacji JSONa wrzuconego jako ciało zapytania

UML:

![alt text](https://raw.githubusercontent.com/er713/IO_jsonTOOL/master/diagram%20klas.png)

Arkusz samooceny, link: https://drive.google.com/file/d/1QW0oihNclFe0EfyoKknbwADBRmrSgpBf/view?usp=sharing

Trello, link: https://trello.com/b/1KIljPBX/json-tools

Travis, link: https://travis-ci.org/er713/IO_jsonTOOL

Zasady punktowania, link: https://docs.google.com/spreadsheets/d/e/2PACX-1vRh3VmprccDy5JC2hxggI1UmWwos2_ukytrvbvvzRT2BkgwskpF3wNIYTx4WXUhAopLKknACRZ65kWt/pubhtml

Planowanie sprintu, link z moodle: https://moodle.put.poznan.pl/pluginfile.php/138442/mod_resource/content/3/Planowanie_sprintu.pdf

![Build status](https://travis-ci.org/er713/IO_jsonTOOL.svg?branch=master)
