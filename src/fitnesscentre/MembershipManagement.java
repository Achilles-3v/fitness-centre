package fitnesscentre;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {
    final private Scanner reader = new Scanner(System.in);

    private int getIntInput() {
        int choice = 0;
        while (choice == 0) {
            try {
                choice = reader.nextInt();
                if (choice == 0)
                    throw new InputMismatchException();
                reader.nextLine();
            }
            catch (InputMismatchException e) {
                reader.nextLine();
                System.out.print("\nERROR: INVALID INPUT. Please try again: ");
            }
        }
        return choice;
    }

    private void printClubOptions() {
        System.out.println("1) Club Mercury");
        System.out.println("2) Club Neptune");
        System.out.println("3) Club Jupiter");
        System.out.println("4) Multi Clubs");
    }

    public int getChoice() {
        System.out.println("\nWELCOME TO OZONE FITNESS CENTER");
        System.out.println("===============================");
        System.out.println("1) Add Member");
        System.out.println("2) Remove Member");
        System.out.println("3) Display Member Information");
        System.out.println("4) Display Members List");
        System.out.print("Please select an option (or Enter -1 to quit): ");

        return getIntInput();
    }

    public String addMembers(LinkedList<Member> m) {
        String name;
        int club;
        String mem;
        double fees;
        int memberID;
        Member mbr;
        Calculator<Integer> cal;

        System.out.print("Please, enter name: ");
        name = reader.nextLine();

        printClubOptions();
        System.out.print("Please, enter ID club: ");
        club = getIntInput();
        while (club < 1 || club > 4) {
            System.out.println("ID NOT FOUND!");
            System.out.print("Please, enter ID club: ");
            club = getIntInput();
        }

        if (m.size() > 0)
            memberID = m.getLast().getMemberID() + 1;
        else
            memberID = 1;

        if (club != 4) {
            cal = (n) -> {
                switch (n) {
                    case 1:
                        return 900;
                    case 2:
                        return 950;
                    case 3:
                        return 1000;
                    default:
                        return -1;
                }
            };
            fees = cal.calculateFees(club);
            mbr = new SingleClubMember('S', memberID, name, fees, club);
            m.add(mbr);
            mem = mbr.toString();
            System.out.println("\nSTATUS: Single club member added\n");
        }
        else {
            cal = (n) -> {
                switch (n) {
                    case 4:
                        return 1200;
                    default:
                        return -1;
                }
            };
            fees = cal.calculateFees(club);
            mbr = new MultiClubMember('M', memberID, name, fees, 100);
            m.add(mbr);
            mem = mbr.toString();
            System.out.println("\nSTATUS: Multi club member added\n");
        }
        return mem;
    }

    public void removeMember(LinkedList<Member> m) {
        int memberID;
        System.out.print("Please, enter member ID to remove: ");
        memberID = getIntInput();
        for (int i = 0; i < m.size(); i++) {
            if (m.get(i).getMemberID() == memberID) {
                m.remove(i);
                System.out.println("\nThis member removed\n");
                return;
            }
        }

        System.out.println("\nMember with ID " + memberID + " not found!\n");
    }

    public void printMemberInfo(LinkedList<Member> m) {
        int memberID;
        System.out.print("Please, enter member ID to display information: ");
        memberID = getIntInput();
        for (int i = 0; i < m.size(); i++) {
            if (m.get(i).getMemberID() == memberID) {
                String[] memberInfo = m.get(i).toString().split(", ");
                System.out.println("\nMember Type: " + memberInfo[0]);
                System.out.println("Member ID: " + memberInfo[1]);
                System.out.println("Member Name: " + memberInfo[2]);
                System.out.println("Member Fees: " + memberInfo[3]);
                if (memberInfo[0].equals("S")) {
                    System.out.println("Club ID: " + memberInfo[4]);
                }
                else
                    System.out.println("Membership Points: " + memberInfo[4]);
                return;
            }
        }
        System.out.println("\nMember ID not found!\n");
    }

    public void printMembersList(LinkedList<Member> m) {
        System.out.println("\n   ID   Name");
        System.out.println("-----------------------------");
        for (int i = 0; i < m.size(); i++) {
            String[] memberInfo = m.get(i).toString().split(", ");
            System.out.printf("%s %3s: %s %n", memberInfo[0], memberInfo[1], memberInfo[2]);
        }
    }
}
