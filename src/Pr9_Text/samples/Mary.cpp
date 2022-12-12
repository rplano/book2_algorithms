#include "Sportler.h"

Sportler::Sportler(){};
Sportler::Sportler(string name, int koerpergewicht, double armlaenge)
{
    _name = name;
    _koerpergewicht = koerpergewicht;
    _armlaenge = armlaenge;
    _energieUmsatz = 0;
}

Sportler::~Sportler() {};

string Sportler::name() const
{
    return _name;
}

int Sportler::koerpergewicht() const
{
    return _koerpergewicht;
}

double Sportler::armlaenge() const
{
    return _armlaenge;
}

double Sportler::energieUmsatz() const
{
    return _energieUmsatz;
}

void Sportler::wiederholungAusfuehren(const Bankdruecken& bankdruecken)   //Energieumsatz des Sportlers ändern
{
    _energieUmsatz = _energieUmsatz + Bankdruecken::ERDBESCHLEUNIGUNG*bankdruecken.eingestelltesGewicht()*armlaenge();  //g*m*h
}


ostream& operator<<(ostream& s,const Sportler& sportler)
{
    s << sportler.name() << ": Koerpergewicht = " << sportler.koerpergewicht() << " kg, Armlaenge = " << sportler.armlaenge() << " m, Energieumsatz = " << sportler.energieUmsatz() << " J";
    return s;
}
