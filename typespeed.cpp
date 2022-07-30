#include<bits/stdc++.h>

#include<ctime>

#include<cstdlib>

#include<fstream>

using namespace std;

//function for fetching Random characters

char getRandomChar();

char pool[] = "abvdefghijklmnopqrstuvwxyz";

int poolsize = sizeof(pool) - 1;

char getRandomChar() {
  return pool[rand() % poolsize];
}

//main function

int main() {

  
  clock_t start, end;  //for calculating time

  string s, s1;

  int score = 0, Highscore = 0;

  // taking inputs from user 
  int p = 1;
  do {
    cout << "Test your typing speed" << endl;

    int n;

    cout << "1.New word 2.Score 3.High score 4.Save and quit" << endl;
    cin >> n;
    s = "";
    s1 = "";
    switch (n) {
    case 1:
      for (int j = 0; j < 6; j++) {
        s += getRandomChar();
      }

      cout << "your new word is=" << s << " " << "Type now" << endl;
     
      //timer starts
      start = clock(); 
      cin >> s1;
      end = clock();
      //timer ends

      if (s == s1) {
        cout << "\nwords matched" << endl;
        double seconds = (double)(end - start) / CLOCKS_PER_SEC;
        cout << "\nyou took " << seconds << " seconds.\n \n";
        if (seconds < 2) {
          score += 2;
          cout << "you scored 2 points and your total score is=" << score << endl;
        } else if (seconds < 3) {
          score += 1;
          cout << "you scored 1 points and your total score is=" << score << endl;
        } else if (seconds > 3) {
          score += 0;
          cout << "you scored 0 points and your total score is=" << score << endl;
        }

      }
        else {
          score -= 1;
          cout << "you scored -1 points and your total score is=" << score << endl;
        }

      break;

    case 2:
      cout << "score = " << score << endl;

      break;

    case 3:
             { ifstream in;
              in.open("highscore.txt");
              in>>Highscore;
              cout << "Highscore = " << Highscore << endl;
              break;}
    case 4:
               p = 4;
              
              if (score > Highscore) {
                 ofstream out;
                 out.open("highscore.txt", ofstream::out | ofstream::trunc);
                 Highscore = score;
                 out<<Highscore;

               }
               cout<<"Saving scores...."<<endl;
                  break;

    default:
                cout << "choose valid option" << endl;
    }
  } while (p != 4);

  return 0;

}