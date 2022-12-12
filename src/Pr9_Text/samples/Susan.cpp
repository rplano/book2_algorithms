#include "Sportler.h"
#include <string>
#include <iostream>
using namespace std;


#pragma once

Sportler::Sportler(string name, int gewicht, double armlaenge)
{
	this->_name = name;
	this->_gewicht = gewicht;
	this->_armlaenge = armlaenge;
	this->_energieumsatz = 0;
}

ostream& operator << (ostream& os, Sportler& op)
{	
	os << op.name() << ": Koerpergewicht = " << op.koerpergewicht() << " kg, Armlaenge = " << op.armlaenge() << " m, Energieumsatz = " << op.energieUmsatz() << " J";
    return os; 
}

ostream& operator << (ostream& os, const Sportler& op)
{	
	os << op.name() << ": Koerpergewicht = " << op.koerpergewicht() << " kg, Armlaenge = " << op.armlaenge() << " m, Energieumsatz = " << op.energieUmsatz() << " J";
    return os; 
}

string Sportler::name() const
{
	return this->_name;
}

int Sportler::koerpergewicht() const
{
	return this->_gewicht;
}

double Sportler::energieUmsatz() const
{
	return this->_energieumsatz;
}

double Sportler::armlaenge() const
{
	return this->_armlaenge;
}

void Sportler::wiederholungAusfuehren(Bankdruecken geraet)
{
	 _energieumsatz += geraet.energieProWiederholung(* this);
}