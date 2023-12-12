import java.util.ArrayList;

class Hero {
    String name;
    double hp;
    double atk;
    double def;
    double mana;
    double damageReceive;
    String category;
    ArrayList<String> skillList = new ArrayList<>();

    Hero(String name, double hp, double atk, double def) {
        this.atk = atk;
        this.name = name;
        this.hp = hp;
        this.def = def;
        this.mana = 100;
        this.damageReceive = 0;
        this.category = "Hero";
        this.skillList.add("Normal Attack");
        this.skillList.add("Heavy Attack");
        this.skillList.add("Raise Attack");
        this.skillList.add("Raise Defense");

    }

    public String toString() {
        return "Character name: " + this.name + " / Class: " + this.category + " / HP: " + this.hp + " / ATK: "
                + this.atk + " / DEF: " + this.def
                + " / MP:  " + this.mana;
    }

    void heavyAttack(Hero other) {
        this.mana -= 50;
        if (this.atk * 5 > other.def) {
            double damage = (this.atk * 5 - other.def);
            System.out.println(this.name + " has dealt " + damage + " damage to " + other.name + " !");
            other.hp -= damage;
            other.damageReceive += damage;
        } else if (this.atk * 5 == other.def) {
            System.out.println("No damage is done!");
        } else {
            double damage = (other.def - this.atk * 5);
            System.out.println(other.name + " has reflected " + damage + " damage to " + this.name + " !");
            this.hp -= damage;
            this.damageReceive += damage;
        }
    }

    void normalAttack(Hero other) {
        this.mana -= 10;
        if (this.atk > other.def) {
            double damage = (this.atk - other.def);
            System.out.println(this.name + " has dealt " + damage + " damage to " + other.name + " !");
            other.hp -= damage;
            other.damageReceive += damage;
        } else if (this.atk == other.def) {
            System.out.println("No damage is done!");
        } else {
            double damage = (other.def - this.atk);
            System.out.println(other.name + " has reflected " + damage + " damage to " + this.name + " !");
            this.damageReceive += damage;
            this.hp -= damage;
        }
    }

    void raiseDefense() {
        this.def += 2000;
        System.out.println(this.name + " has raised defense up to " + this.def);
    }

    void raiseAttack() {
        this.atk += 1000;
        System.out.println(this.name + " has raised defense up to " + this.atk);
    }

    void counterAttack(Hero other) {
    }

    void berserkMode() {
    }

    void sacrificeAttack(Hero other) {
    }
}

class Saber extends Hero {

    Saber(String name, double hp, double atk, double def) {
        super(name, hp, atk, def);
        this.category = "Saber";
        this.skillList.remove("Heavy Attack");
        this.skillList.add(1, "Counter Attack");
    }

    void counterAttack(Hero other) {
        if (this.damageReceive <= other.def) {
            System.out.println("No damage has been done !");
        } else {
            double damage = (this.damageReceive * 3) - other.def;
            System.out.println(this.name + "has deal " + damage + " damage to " + other.name + " !");
            other.damageReceive += damage;
            other.hp -= damage;
        }
        this.mana -= 50;
    }
}

class Berserker extends Hero {
    boolean berserkMode;

    Berserker(String name, double hp, double atk, double def) {
        super(name, hp, atk, def);
        this.berserkMode = false;
        this.category = "Berserker";
        this.skillList.remove("Heavy Attack");
        this.skillList.remove("Raise Attack");
        this.skillList.add(1, "Sacrifice Attacck");
        this.skillList.add(2, "Berserk Mode");
    }

    void berserkMode() {
        if (this.berserkMode == false) {
            this.berserkMode = true;
            this.def = 1;
            this.atk += 3000;
            System.out.println(this.name + " has enter Berserk mode!");
        } else {
            this.berserkMode = false;
            this.def += 2000;
            this.atk -= 3000;
            System.out.println(this.name + " has quit Berserk mode!");
        }
    }

    void sacrificeAttack(Hero other) {
        this.mana -= 50;
        if (this.hp <= 1000) {
            this.hp = 1;
            this.atk *= this.atk;
        }
        if (this.berserkMode == true) {
            while (this.hp > 1000) {
                this.hp -= 1000;
                this.atk += 1000;
                System.out.println(
                        this.name + "raising ATK up to " + this.atk + " , and still have " + this.hp + " HP left!");
                System.out.print("Press Enter to countinue or enter n to stop sacrificing ");
                String pause = In.nextLine();
                if (pause.equals("n")) {
                    break;
                }
            }
        } else {
            this.hp -= 1000;
            this.atk += 1000;
        }
        System.out.println("Finish sacrificing, " + this.name + " has raise ATK up to " + this.atk + " , HP left is "
                + this.hp + " !");
        if (this.atk * 3 == other.def) {
            System.out.println("No damage has been done!");
        } else if (this.atk * 3 < other.def) {
            double damage = other.def - (this.atk * 3);
            System.out.println(other.name + " has reflected " + damage + " damage to " + this.name + " !");
            this.hp -= damage;
            this.damageReceive += damage;
        } else {
            double damage = this.atk * 3 - other.def;
            System.out.println(this.name + " has dealt " + damage + " damage to " + other.name + " !");
            other.hp -= damage;
            other.damageReceive += damage;
        }

    }
}

class Tournament {
    String name;
    Hero player1;
    Hero player2;
    int turnLimit;
    String place;
    double hpMod;
    double atkMod;
    double defMod;

    Tournament(String name, String place, double hpMod, double atkMod, double defMod) {
        this.name = name;
        this.place = place;
        this.hpMod = hpMod;
        this.atkMod = atkMod;
        this.defMod = defMod;
        this.turnLimit = 20;
    }

    @Override
    public String toString() {
        return "The tournament name is " + this.name + " , held in " + this.place
                + "! \nAdjustment details: ATK modified by " + this.atkMod + " , DEF modified by " + this.defMod
                + " , HP modified by " + this.hpMod + " !";
    }

    void pickCharacter(ArrayList<Hero> characters) {
        int player = 0;
        while (player < 2) {
            int c = 0;
            while (c < characters.size()) {
                System.out.println("[" + (c + 1) + "]" + " " + characters.get(c));
                c += 1;
            }
            if (player == 0) {
                System.out.print("Player 1 choose a character: ");
                int charChoice = In.nextInt();
                if (charChoice > characters.size() | charChoice <= 0) {
                    System.out.println("Invalid input!");
                    continue;
                } else {
                    player1 = characters.get(charChoice - 1);
                    if (player1.category.equals("Hero")) {
                        Hero playerHold1 = new Hero(this.player1.name, this.player1.hp, this.player1.atk,
                                this.player1.def);
                        player1 = playerHold1;
                    } else if (player1.category.equals("Saber")) {
                        Hero playerHold1 = new Saber(this.player1.name, player1.hp, player1.atk, player1.def);
                        player1 = playerHold1;
                    } else {
                        Hero playerHold1 = new Berserker(player1.name, player1.hp, player1.atk, player1.def);
                        player1 = playerHold1;
                    }
                    System.out.println("Player 1 has picked " + player1.name);
                    player += 1;
                }
            } else {
                System.out.print("Player 2 choose a character: ");
                int charChoice = In.nextInt();
                if (charChoice > characters.size() | charChoice <= 0) {
                    System.out.println("Invalid input!");
                    continue;
                } else {
                    this.player2 = characters.get(charChoice - 1);
                    if (player2.category.equals("Hero")) {
                        Hero playerHold2 = new Hero(this.player2.name, this.player2.hp, this.player2.atk,
                                this.player2.def);
                        player2 = playerHold2;
                    } else if (player2.category.equals("Saber")) {
                        Hero playerHold2 = new Saber(this.player2.name, player2.hp, player2.atk, player2.def);
                        player2 = playerHold2;
                    } else {
                        Hero playerHold2 = new Berserker(player2.name, player2.hp, player2.atk, player2.def);
                        player2 = playerHold2;
                    }
                    System.out.println("Player 2 has picked " + player2.name);
                    player += 1;
                }
            }
        }
        System.out.print("Please set turn limit (Maximum 20 Turn, any other value will set turn limit to 20!) : ");
        this.turnLimit = In.nextInt();
        if (this.turnLimit < 0 | this.turnLimit > 20) {
            System.out.println("You have entered invalid number, your turn is set to 20!");
            this.turnLimit = 20;
        } else {
            System.out.println("You have set turn limit to " + this.turnLimit);
        }
        this.player1.hp += this.hpMod;
        this.player1.atk += this.atkMod;
        this.player1.def += this.defMod;
        this.player2.hp += this.hpMod;
        this.player2.atk += this.atkMod;
        this.player2.def += this.defMod;
    }

    void combat() {
        System.out.println("                                                    Battle Begin!");
        while (player1.hp > 0 && player2.hp > 0) {
            if (this.turnLimit <= 0) {
                if (player1.hp > player2.hp) {
                    System.out.println("Player 1 wins the battle!");
                    break;
                } else if (player2.hp > player1.hp) {
                    System.out.println("Player 2 wins the battle!");
                    break;
                } else {
                    System.out.println("Battle Draw!");
                    break;
                }
            }
            System.out
                    .println("                                                    " + this.turnLimit + " turn left");
            System.out.println("========================================================================");
            System.out.println(player1.name + " : " + player1.hp + " HP / " + player1.atk + " ATK / " + player1.def
                    + " DEF / " + player1.mana + " MP");
            System.out.println("========================================================================");
            System.out.println(player2.name + " : " + player2.hp + " HP / " + player2.atk + " ATK / " + player2.def
                    + " DEF / " + player2.mana + " MP");
            while (true) {
                System.out.println("Player 1 turn!");
                int skill = 0;
                while (skill < player1.skillList.size()) {
                    System.out.print("[" + (skill + 1) + "] " + player1.skillList.get(skill) + " ");
                    skill++;
                }
                System.out.println();
                System.out.print("Player 1 choose your skill: ");
                int skillChoice = In.nextInt();
                if (skillChoice == 1) {
                    if (player1.mana < 10) {
                        System.out.println("Not enough mana, choose another skill!");
                        continue;
                    } else {
                        player1.normalAttack(player2);
                        break;
                    }
                } else if (skillChoice == 2) {
                    if (player1.mana < 50) {
                        System.out.println("Not enough mana!, please choose again");
                        continue;
                    } else {
                        if (player1.category.equals("Hero")) {
                            player1.heavyAttack(player2);
                        } else if (player1.category.equals("Saber")) {
                            player1.counterAttack(player2);
                        } else {
                            player1.sacrificeAttack(player2);
                        }
                        break;
                    }
                } else if (skillChoice == 3) {
                    if (player1.category.equals("Berserker")) {
                        player1.berserkMode();
                    } else {
                        player1.raiseAttack();
                    }
                    break;
                } else if (skillChoice == 4) {
                    player1.raiseDefense();
                    break;
                } else {
                    System.out.println("Wrong input!");
                    continue;
                }
            }
            if (player2.hp <= 0) {
                System.out.println("Player 1 win the game!");
                break;
            }
            // player2
            System.out
                    .println("                                                    " + this.turnLimit + " turn left");
            System.out.println("========================================================================");
            System.out.println(player1.name + " : " + player1.hp + " HP / " + player1.atk + " ATK / " + player1.def
                    + " DEF / " + player1.mana + " MP");
            System.out.println("========================================================================");
            System.out.println(player2.name + " : " + player2.hp + " HP / " + player2.atk + " ATK / " + player2.def
                    + " DEF / " + player2.mana + " MP");
            while (true) {
                System.out.println("Player 2 turn!");
                int skill = 0;
                while (skill < player2.skillList.size()) {
                    System.out.print("[" + (skill + 1) + "] " + player2.skillList.get(skill) + " ");
                    skill++;
                }
                System.out.println();
                System.out.print("Player 2 choose your skill: ");
                int skillChoice = In.nextInt();
                if (skillChoice == 1) {
                    if (player2.mana < 10) {
                        System.out.println("Not enough mana, choose another skill!");
                        continue;
                    } else {
                        player2.normalAttack(player1);
                        break;
                    }
                } else if (skillChoice == 2) {
                    if (player2.mana < 50) {
                        System.out.println("Not enough mana!, please choose again");
                        continue;
                    } else {
                        if (player2.category.equals("Hero")) {
                            player2.heavyAttack(player1);
                        } else if (player2.category.equals("Saber")) {
                            player2.counterAttack(player1);
                        } else {
                            player2.sacrificeAttack(player1);
                        }
                        break;
                    }
                } else if (skillChoice == 3) {
                    if (player2.category.equals("Berserker")) {
                        player2.berserkMode();
                    } else {
                        player2.raiseAttack();
                    }
                    break;
                } else if (skillChoice == 4) {
                    player2.raiseDefense();
                    break;
                } else {
                    System.out.println("Wrong input!");
                    continue;
                }
            }
            if (player1.hp <= 0) {
                System.out.println("Player 2 win the game!");
                break;
            }
            this.turnLimit -= 1;
            player1.mana += 20;
            player2.mana += 20;
        }
    }
}

public class ProjectB {
    public static void main(String[] args) {
        Hero h1 = new Hero("John", 13000, 3000, 2000);
        Hero h2 = new Hero("Mike", 13000, 2000, 3000);
        Hero h3 = new Hero("Tom", 14000, 2000, 3000);
        Hero h4 = new Hero("Tim", 12500, 2500, 3500);
        Hero s1 = new Saber("James", 20000, 1000, 0);
        Hero s2 = new Saber("Patt", 17000, 2000, 0);
        Hero s3 = new Saber("Ryan", 18000, 1500, 0);
        Hero s4 = new Saber("Bill", 19000, 1500, 0);
        Hero b1 = new Berserker("Michael", 10000, 1000, 3000);
        Hero b2 = new Berserker("Franklin", 13000, 1000, 1000);
        Hero b3 = new Berserker("Trevor", 13000, 2000, 0);
        Hero b4 = new Berserker("Jack", 10000, 1000, 3000);
        ArrayList<Hero> characters = new ArrayList<>();
        characters.add(h1);
        characters.add(h2);
        characters.add(h3);
        characters.add(h4);
        characters.add(s1);
        characters.add(s2);
        characters.add(s3);
        characters.add(s4);
        characters.add(b1);
        characters.add(b2);
        characters.add(b3);
        characters.add(b4);
        Tournament t1 = new Tournament("Street Fight", "Sydney", 1000, 1000, 3000);
        Tournament t2 = new Tournament("Underground Fight", "New York", 0, 3000, 3000);
        Tournament t3 = new Tournament("Subway Fight", "London", 5000, 100, 0);
        Tournament t4 = new Tournament("Suber Fight", "Paris", 0, 300, 5000);
        Tournament[] tours = { t1, t2, t3, t4 };
        int point1 = 0;
        int point2 = 0;
        System.out.println("                                       Welcome to 2023 combat game!");
        while (true) {
            System.out.println("This game user number input correspond to the number display on screen");
            System.out.println("[1] Play game");
            System.out.println("[2] Quit");
            System.out.print("Choose an option: ");
            int choice = In.nextInt();
            if (choice < 1 || choice > 2) {
                System.out.println("Wrong input!");
                continue;
            }
            if (choice == 2) {
                break;
            } else if (choice == 1) {
                int count = 1;
                for (Tournament t : tours) {
                    System.err.println("[" + count + "] " + t);
                    count++;
                }
                System.out.print("Pick a tournament (Press other number for exit): ");
                int t = In.nextInt();
                if (t < 1 || t > 4) {
                    System.out.println("Back to menu");
                    continue;
                }
                Tournament tour = tours[t - 1];
                tour.pickCharacter(characters);
                tour.combat();
                if (tour.player1.hp < tour.player2.hp) {
                    point2 += 1;
                } else if (tour.player2.hp < tour.player1.hp) {
                    point1 += 1;
                }
                System.out
                        .println("Player 1: " + point1 + " Point \n" + "========================================\n"
                                + "Player 2: " + point2 + " Point");
            }
        }
        if (point1 > point2) {
            System.out.println("Player 1 win overall!");
        } else if (point2 > point1) {
            System.out.println("Player 2 win overall!");
        } else {
            System.out.println("No one win!");
        }
        System.out.println("Thank you for playing the game!");
    }
}