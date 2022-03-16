import java.util.Stack;

/**
 * We will create a stack and push all the id’s in the stack.
 * Run a loop while there are more than 1 element in the stack.
 * Pop top two element from the stack (represent them as A and B)
 * If A knows B, then A can’t be a celebrity and push B in stack.
 * Else if A doesn’t know B, then B can’t be a celebrity push A in stack.
 * Assign the remaining element in the stack as the celebrity.
 * Run a loop from 0 to n-1 and find the count of persons who knows the celebrity
 * and the number of people whom the celebrity knows.
 * if the count of persons who knows the celebrity is n-1
 * and the count of people whom the celebrity knows is 0 then return the id of celebrity else return -1.
 */
public class FindingCelebrity
{
    // In this , Person with ID 2 is celebrity as he knows no one
    int MATRIX[][] = { { 0, 0, 1, 0 },
            { 0, 0, 1, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 1, 0 } };

    // it will return true if a knows b,otherwise false

     boolean knows(int a, int b)

    {
        boolean res = (MATRIX[a][b] == 1) ?
                true :
                false;
        return res;
    }
    //it will return -1 if celebrity is not present. If present, it returns id (value from 0 to n-1).

    int findingCelebrity(int n)
    {
        Stack<Integer> st = new Stack<>();
        int c;
        // First , we will push everybody on to stack

        for (int i = 0; i < n; i++)
        {
            st.push(i);
        }
        while (st.size() > 1)
        {
            //Now, we will pop off top two persons from the stack and discard one based on return status of knows(A,B).
            int a = st.pop();
            int b = st.pop();
            // we will now push the remained person on stack again
            if (knows(a, b))
            {
                st.push(b);
            }
            else
                st.push(a);
        }

        // If there are only two people on stack and there is no potential candidate
        if(st.empty())
            return -1;
        c = st.pop();

     // Now we will check if the last person is celebrity or not
        for (int i = 0; i < n; i++)
        {
            // If any person doesn't know 'c' or 'a' doesn't know any person, return -1

            if (i != c && (knows(c, i) ||
                    !knows(i, c)))
                return -1;
        }
        return c;
    }


    // Main Block to run above code
    public static void main(String[] args)
    {
        int n = 4;
        FindingCelebrity obj=new FindingCelebrity();
        int result = obj.findingCelebrity(n);
        if (result == -1)
        {
            System.out.println("No Celebrity is present at the party");
        }
        else
            System.out.println("Celebrity ID is = "+result);
    }
}
 