#include <iostream>
#include <cstdlib>
using namespace std;

struct Elem {
	int vred;
	Elem* veza = nullptr;
};
Elem* stekP = nullptr;
void PUSH(int a) {
	Elem *tmp = new Elem;
	tmp->vred = a;
	tmp->veza = stekP;
	stekP = tmp;
}
int POP() {
	Elem *tmp = stekP;
	stekP = stekP->veza;
	int a = tmp->vred;
	delete tmp;
	return a;
}
bool EMPTY() {
	return stekP == nullptr;
}
void CLEAR() {
	while (stekP) {
		Elem *tmp = stekP;
		stekP = stekP->veza;
		delete tmp;
	}
}


void kreirajGraf(int **&graf, int n) {
	graf = new int*[n];
	for (int i = 0; i < n; i++) {
		graf[i] = new int[n];
		for (int j = 0; j < n; j++)
			graf[i][j] = 0;
	}
}
void brisiGraf(int **&graf, int &n) {
	for (int i = 0; i < n; i++)
		delete[] graf[i];
	delete[] graf;
	graf = nullptr;
	n = 0;
}
void dodajGranu(int **&graf, int cvor1, int cvor2) {
	graf[cvor1][cvor2] = graf[cvor2][cvor1] = 1;
}
void brisiGranu(int **&graf, int cvor1, int cvor2) {
	graf[cvor1][cvor2] = graf[cvor2][cvor1] = 0;
}
void ispisGrafa(int **&graf, int n) {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++)
			cout << graf[i][j] << " ";
		cout << endl;
	}
}

void kreirajLavirint(char **&lavirint, int sirina, int duzina, int ulazI, int ulazJ, int izlazI, int izlazJ) {
	//Kreira laviirnt i ubacuje ulaz i izlaz
	int sirinaIspisa = 2 * sirina + 1;
	int duzinaIspisa = 2 * duzina + 1;
	lavirint = new char*[duzinaIspisa];

	//int dimenzijaGrafa = sirina * duzina;

	lavirint = new char*[duzinaIspisa];
	for (int i = 0; i < duzinaIspisa; i++) { // lavirint od zidova
		lavirint[i] = new char[sirinaIspisa];
		for (int j = 0; j < sirinaIspisa; j++)
			lavirint[i][j] = 'X';
	}
	for (int i = 0; i < duzina; i++) // popunjavanje cvorova grafova zidovima.
		for (int j = 0; j < sirina; j++) {
			lavirint[2 * i + 1][2 * j + 1] = ' ';
		}

	//Ulaz
	lavirint[2 * ulazI + 1][2 * ulazJ + 1] = 'O';
	if (2 * ulazI + 1 == 1) // gore
		lavirint[2 * ulazI][2 * ulazJ + 1] = ' ';

	if (2 * ulazJ + 1 == 1) // levo
		lavirint[2 * ulazI + 1][2 * ulazJ] = ' ';

	if (2 * ulazI + 1 == 1 && 2 * ulazJ + 1 == 1) //00 cosak
		lavirint[2 * ulazI][2 * ulazJ] = ' ';

	if (2 * ulazI + 1 == duzinaIspisa - 2) // dole 
		lavirint[2 * ulazI + 2][2 * ulazJ + 1] = ' ';

	if (2 * ulazJ + 1 == sirinaIspisa - 2) // desno
		lavirint[2 * ulazI + 1][2 * ulazJ + 2] = ' ';

	if (2 * ulazI + 1 == duzinaIspisa - 2 && 2 * ulazJ + 1 == sirinaIspisa - 2) // 11 cosak
		lavirint[2 * ulazI + 2][2 * ulazJ + 2] = ' ';
	
	if (2 * ulazI + 1 == 1 && 2 * ulazJ + 1 == sirinaIspisa - 2) // 01 cosak
		lavirint[2 * ulazI][2 * ulazJ + 2] = ' ';

	if (2 * ulazI + 1 == duzinaIspisa - 2 && 2 * ulazJ + 1 == 1) //10 cosak
		lavirint[2 * ulazI + 2][2 * ulazJ] = ' ';

//Izlaz
	lavirint[2 * izlazI + 1][2 * izlazJ + 1] = 'I';

	if (2 * izlazI + 1 == 1) // gore
		lavirint[2 * izlazI][2 * izlazJ + 1] = ' ';

	if (2 * izlazJ + 1 == 1) // levo
		lavirint[2 * izlazI + 1][2 * izlazJ] = ' ';

	if (2 * izlazI + 1 == 1 && 2 * izlazJ + 1 == 1) //00 cosak
		lavirint[2 * izlazI][2 * izlazJ] = ' ';

	if (2 * izlazI + 1 == duzinaIspisa - 2) // dole 
		lavirint[2 * izlazI + 2][2 * izlazJ + 1] = ' ';

	if (2 * izlazJ + 1 == sirinaIspisa - 2) // desno
		lavirint[2 * izlazI + 1][2 * izlazJ + 2] = ' ';

	if (2 * izlazI + 1 == duzinaIspisa - 2 && 2 * izlazJ + 1 == sirinaIspisa - 2) // 11 cosak
		lavirint[2 * izlazI + 2][2 * izlazJ + 2] = ' ';

	if (2 * izlazI + 1 == 1 && 2 * izlazJ + 1 == sirinaIspisa - 2) // 01 cosak
		lavirint[2 * izlazI][2 * izlazJ + 2] = ' ';

	if (2 * izlazI + 1 == duzinaIspisa - 2 && 2 * izlazJ + 1 == 1) //10 cosak
		lavirint[2 * izlazI + 2][2 * izlazJ] = ' ';

}
void ispisLavirinta(int **&graf, int m, int n, int ulazI, int ulazJ, int izlazI, int izlazJ) {
	// m je duzina grafa
	// n je sirina grafa
	int dimIspM = 2 * m + 1; // ispisne dimenzije
	int dimIspN = 2 * n + 1;
	char **ispis;

	kreirajLavirint(ispis, n, m, ulazI, ulazJ, izlazI, izlazJ);
	// obrada lavirinta
	//ispis = new char*[dimIspM];
	//for (int i = 0; i < dimIspM; i++) {
	//	ispis[i] = new char[dimIspN];
	//	for (int j = 0; j < dimIspN; j++)
	//		ispis[i][j] = 'X';
	//}
	//for (int i = 0; i < m; i++)
	//	for (int j = 0; j < n; j++) {
	//		int i1 = 2 * i + 1, j1 = 2 * j + 1;
	//		ispis[i1][j1] = '-';
	//	}
	//ispis[2 * ulazI + 1][ 2 * ulazJ + 1] = 'O';
	//if(2 * ulazI + 1 == 1)
	//	ispis[2 * ulazI][2 * ulazJ + 1] = ' ';
	//if (2 * ulazJ + 1 == 1)
	//	ispis[2 * ulazI + 1][2 * ulazJ] = ' ';
	//if (2 * ulazI + 1 == 1 && 2 * ulazJ + 1 == 1)
	//	ispis[2 * ulazI][2 * ulazJ] = ' ';
	//ispis[2 * izlazI + 1][2 * izlazJ + 1] = 'I';
	//if(2 * izlazI + 1 == dimIspM - 2)
	//	ispis[2 * izlazI  + 2][2 * izlazJ + 1] = ' ';
	//
	//if (2 * izlazJ + 1 == dimIspN - 2)
	//	ispis[2 * izlazI + 1][2 * izlazJ + 2] = ' ';
	//
	//if (2 * izlazI + 1 == dimIspM - 2 && 2 * izlazJ + 1 == dimIspN - 2)
	//	ispis[2 * izlazI + 2][2 * izlazJ + 2] = ' ';
	
	
	//Obrada grafa
	int brojGraf = m * n;
	for (int i = 0; i < brojGraf; i++)
		for (int j = i + 1; j < brojGraf; j++) {
			if (graf[i][j] == 1) {
				int i1 = i / n, j1 = i % n;
				int i2 = j / n, j2 = j % n;
				int iIspis1 = 2 * i1 + 1, jIspis1 = 2 * j1 + 1;
				int iIspis2 = 2 * i2 + 1, jIspis2 = 2 * j2 + 1;

				//Dodavanje medjugrane u matricu ispisa da se poveze sa cvorovima
				if (j1 == j2) {
					//cout << "Dodavanje -: " << iIspis1 << " " << jIspis1 << " " << iIspis2 << " " << jIspis2 << endl;
					ispis[(iIspis1 + iIspis2) / 2][jIspis1] = ' ';
				}
				if (i1 == i2) {
					ispis[iIspis1][(jIspis1 + jIspis2) / 2] = ' ';
				}

			}
		}
	
	for (int i = 0; i < dimIspM; i++) { // ispis celog lavirinta
		for (int j = 0; j < dimIspN; j++)
			cout << ispis[i][j];
		cout << endl;
	}

	//Brisanje matrice char** - oslobadjanje memorije
	for (int i = 0; i < n; i++) delete[] ispis[i];
	delete[] ispis;
}

void dodajProlaz(int **&graf, int n, int i1, int j1, int i2, int j2) {
	//dodavanje prolaza izmedju 2 polja lavirinta
	int cvor1 = i1 * n + j1;
	int covr2 = i2 * n + j2;
	dodajGranu(graf, cvor1, covr2);
}
void brisiProlaz(int **&graf, int n, int i1, int j1, int i2, int j2) {
	//brisanje prolaza izmedju 2 polja lavirinta
	int cvor1 = i1 * n + j1;
	int covr2 = i2 * n + j2;
	brisiGranu(graf, cvor1, covr2);
}



void dfsIteretivno(int **graf, int n, bool *posecen, int v, int vKraj, int nLav) {
	// Ukoliko je potrebna fizicka predstava grafa zajedno sa poljima na njoj otkomentarisati
	//int *a = new int[n], i = 0;
	PUSH(v);
	cout << "U fizickom lavirintu: Ici redom na polja:" << endl << "Ulaz -> ";
	while (!(EMPTY())) {
		v = POP();
		if (posecen[v] == false) {
			posecen[v] = true;
			cout << "(" << v / nLav <<","<< v % nLav << ") -> ";
			//a[i++] = v;
			if (v == vKraj) {
				cout << "Izlaz" << endl;
				//cout << "U ispisanom lavirintu: Ici redom na polja:" << endl << "Ulaz -> ";
				//for (int j = 0; j < i; j++)
				//	cout << "(" << 2 * (a[j] / nLav) + 1 << "," << 2 * (a[j] % nLav) +1 << ") -> ";
				//cout << "Izlaz" << endl;
				//delete[] a;
				CLEAR();
				return;
			}
			for (int u = 0; u < n; u++)
				if (graf[v][u] == 1 && posecen[u] == false)
					PUSH(u);
		}
	}
	cout << "Zid" << endl;
	//delete[] a;
	if(vKraj != -1)
		cout << "Obidjeni su svi cvorovi i nije nadjen izlaz iz lavirinta!" << endl;
}

void dfs(int **graf, int n, int nLav, int pocetniCvor = 0, int krajnjiCvor = -1) {
	bool* posecen = new bool[n] {false};
	//int* posecen = new int[n] {0};

	//for (int i = 0; i < n; i++)
	//	cout << posecen[i] << " ";
	//cout << endl;

	dfsIteretivno(graf, n, posecen, pocetniCvor, krajnjiCvor, nLav);

	//for (int i = 0; i < n; i++)
	//	cout << posecen[i] << " ";
	//cout << endl;

}

int main(void) {
	int mLav, nLav;
	
	int ulazI, ulazJ, izlazI, izlazJ;
	
	int **graf = nullptr, brCvorova = 0;
	//brCvorova = mLav * nLav;

	//kreirajGraf(graf, brCvorova);
	////ispisLavirinta(graf, mLav, nLav, ulazI, ulazJ, izlazI, izlazJ);
	//
	//
	////int i1 = 0, j1 = 1;
	////int i2 = 1, j2 = 1;
	//dodajProlaz(graf, nLav, 0, 0, 1, 0);
	//dodajProlaz(graf, nLav, 1, 0, 2, 0);
	//dodajProlaz(graf, nLav, 2, 1, 2, 2);
	//dodajProlaz(graf, nLav, 2, 0, 2, 1);
	//dodajProlaz(graf, nLav, 1, 0, 1, 1);
	//dodajProlaz(graf, nLav, 2, 0, 3, 0);
	//dodajProlaz(graf, nLav, 0, 2, 1, 2);
	////ispisGrafa(graf, brCvorova);
	//ispisLavirinta(graf, mLav, nLav, ulazI, ulazJ, izlazI, izlazJ);
	//dfs(graf, brCvorova, nLav, 3*ulazI + ulazJ, 3*izlazI + izlazJ);
	//brisiGraf(graf, brCvorova);
	/*dodajGranu(graf, 0, 1);
	dodajGranu(graf, 0, 3);
	dodajGranu(graf, 1, 4);
	dodajGranu(graf, 1, 5);
	dodajGranu(graf, 3, 1);
	dodajGranu(graf, 4, 3);
	dodajGranu(graf, 5, 2);
	dodajGranu(graf, 5, 4);
	dodajGranu(graf, 5, 6);
	dodajGranu(graf, 6, 2);
	*/
	int izbor = 1;
	while (izbor) {

		cout << endl << "Izabrati redni broj radnje: " << endl;
		cout << "1. Kreiranje lavirinta" << endl;
		cout << "2. Dodavanje puta izmedju 2 polja u lavirintu" << endl;
		cout << "3. Brisanje puta izmedju 2 polja u lavirintu" << endl;
		cout << "4. Resavanje lavirinta" << endl;
		cout << "5. Ispis lavirinta" << endl;
		cout << "6. Brisanje lavirinta" << endl;
		cout << "0. Prekid rada programa" << endl;
		cin >> izbor;
		switch (izbor) {

			int koordI1, koordJ1, koordI2, koordJ2;

		case 1:
			brisiGraf(graf, brCvorova); // novi se kreira
			cout << "Uneti dimentije lavirinta: " << endl;
			cout << "Sirina: "; cin >> nLav;
			cout << "Duzina: "; cin >> mLav;
			cout << "Uneti koordinate ulaza u lavirint: " << endl;
			cout << "Ulaz (2 koordinate u formatu VRSTA KOLONA): "; cin >> ulazI >> ulazJ;
			cout << "Izlaz (2 koordinate u formatu VRSTA KOLONA): "; cin >> izlazI >> izlazJ;
				
			if (nLav <= 0 || mLav <= 0) {
				cout << "Neispravne dimenzije lavirinta!" << endl;
				break;
			}
			if (ulazI < 0 || ulazJ < 0 || izlazI < 0 || izlazJ < 0 ||
				ulazI >= mLav || ulazJ >= nLav || izlazI >= mLav || izlazJ >= nLav) {
				cout << "Ulaz ili izlaz nisu u lavirintu!" << endl;
				break;
			}
			if (ulazI == izlazI && ulazJ == izlazJ) {
				cout << "Ulaz i izlaz se poklapaju -> resen lavirint" << endl;
				break;
			}
			if (ulazI * ulazJ == 0 || ulazI == mLav - 1 || ulazJ == nLav - 1 ||
				izlazI * izlazJ == 0 || izlazI == mLav - 1 || izlazJ == nLav - 1) {
				brCvorova = nLav * mLav;
				kreirajGraf(graf, brCvorova);
				break;
			}
			else{
				cout << "Ulaz ili izlaz su u unutrasnjosti lavirinta!" << endl;
				break;
			}

		case 2:
			if (graf == nullptr) {
				cout << "Lavirint je prazan" << endl;
				break;
			}
			cout << "Polja lavirinta izmedju kojih se formira put:" << endl;
			cout << "Prvo polje (2 koordinate u formatu VRSTA KOLONA): "; cin >> koordI1 >> koordJ1;
			cout << "Drugo polje (2 koordinate u formatu VRSTA KOLONA): "; cin >> koordI2 >> koordJ2;
			if (koordI1 < 0 || koordI2 < 0 || koordJ1 < 0 || koordJ2 < 0 ||
				koordI1 >= mLav || koordI2 >= mLav || koordJ1 >= nLav || koordJ2 >= nLav) {
				cout << "Polja nisu u lavirintu!" << endl;
				break;
			}
			dodajProlaz(graf, nLav, koordI1, koordJ1, koordI2, koordJ2);
			break;
		case 3:
			if (graf == nullptr) {
				cout << "Lavirint je prazan" << endl;
				break;
			}
			cout << "Polja lavirinta izmedju kojih se brise put:" << endl;
			cout << "Prvo polje (2 koordinate u formatu: VRSTA KOLONA): "; cin >> koordI1 >> koordJ1;
			cout << "Drugo polje (2 koordinate u formatu: VRSTA KOLONA): "; cin >> koordI2 >> koordJ2;
			if (koordI1 < 0 || koordI2 < 0 || koordJ1 < 0 || koordJ2 < 0 ||
				koordI1 >= mLav || koordI2 >= mLav || koordJ1 >= nLav || koordJ2 >= nLav) {
				cout << "Polja nisu u lavirintu!" << endl;
				break;
			}
			brisiProlaz(graf, nLav, koordI1, koordJ1, koordI2, koordJ2);
			break;
		case 4:
			if (graf == nullptr) {
				cout << "Lavirint je prazan" << endl;
				break;
			}
			dfs(graf, brCvorova, nLav, nLav * ulazI + ulazJ, nLav * izlazI + izlazJ);
			break;
		case 5: 
			if (graf == nullptr) {
				cout << "Lavirint je prazan" << endl;
				break;
			}
			ispisLavirinta(graf, mLav, nLav, ulazI, ulazJ, izlazI, izlazJ); 
			break;
		case 6: brisiGraf(graf, brCvorova); 
			break;

		case 0: brisiGraf(graf, brCvorova); cout << endl << "Kraj programa" << endl;  
			break;

		default: cout << "Pogresan izbor, uneti ponovo" << endl; break;
		}
	}
	system("pause");
	return 0;
}
