#include "Sportler.h"
#include "Bankdruecken.h"
#include <string.h>
#include <iostream>
#include <sstream>

class Bankdruecken;

using namespace std;

Sportler::Sportler(string _name, int _koerpergewicht, double _armlaenge)
{
    this->_name = _name;
    this->_koerpergewicht = _koerpergewicht;
    this->_armlaenge = _armlaenge;
    this->_energieUmsatz = 0;
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

void Sportler::energieErhoehen(const double energie)
{
    this->_energieUmsatz += energie;
}

void Sportler::wiederholungAusfuehren(const Bankdruecken& a)
{
    Sportler::energieErhoehen(a.eingestelltesGewicht()*Bankdruecken::ERDBESCHLEUNIGUNG*Sportler::armlaenge());
}

ostream& operator<<(ostream& os, const Sportler& a)
{
    os << a.name() << ": Koerpergewicht = " << a.koerpergewicht() << " kg, Armlaenge = " << a.armlaenge() << " m, Energieumsatz = " << a.energieUmsatz() << " J";
    return os;
}
