#include "Sportler.h"

using namespace std;

Sportler::Sportler(char* name,int koerpergewicht,double armlaenge)
{
        _name = name;
        _koerpergewicht = koerpergewicht;
        _armlaenge = armlaenge;
        _energieUmsatz = 0;
}

char* Sportler::name() const
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

void Sportler::wiederholungAusfuehren(const Bankdruecken& bank)
{
    _energieUmsatz += bank.eingestelltesGewicht()*Bankdruecken::ERDBESCHLEUNIGUNG*_armlaenge;
}

ostream& operator<<(ostream& os, const Sportler& sportler)
{
    os << sportler.name()<<": Körpergewicht = "<<sportler.koerpergewicht()<< " kg, Armlänge = "<<sportler.armlaenge()<<" m, Energieumsatz = "<<sportler.energieUmsatz()<<" J";
    return os;
}

