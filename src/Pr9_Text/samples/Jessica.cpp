#include "Sportler.h"

Sportler::Sportler()
{
        std::string _name = "<Not Defined>";
        this->_koerpergewicht = 0;
        this->_armlaenge= 0;
        this->_energieumsatz = 0;
}

Sportler::Sportler(std::string _name, int _koerpergewicht, double _armlaenge)
{
    this->_name = _name;
    this->_koerpergewicht = _koerpergewicht;
    this->_armlaenge = _armlaenge;
    this->_energieumsatz = 0;
}

std::string Sportler::name() const
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

    return _energieumsatz;
}

void Sportler::set_energieUmsatz(double umrechnungsfaktor)
{
    _energieumsatz /= umrechnungsfaktor;
}

std::ostream& operator<< (std::ostream& os, const Sportler& s)
{
   return os << s.name() << ": Körpergewicht = " << s.koerpergewicht() << " kg, Armlänge = " << s.armlaenge() << " m, Energieumsatz = " << s.energieUmsatz() << " J";
}

void Sportler::wiederholungAusfuehren(Bankdruecken bank)
{
    _energieumsatz += bank.energieProWiederholung(*this);
}
