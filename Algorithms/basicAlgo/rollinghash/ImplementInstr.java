public class ImplementInstr {
    //Rolling hash
    class Solution {
        int base = 256;
        int mod = 1000000007;
    
        public int strStr(String h, String n) {
            if (n.isEmpty()) {
                return 0;
            }   
            if (h.length() < n.length()) {
                return -1;
            }
            
            int len = n.length();
            long hash = 0;
            long targetHash = 0;
            long pow = 1;
            
            for (int i = 0 ; i < n.length(); i++) {
                hash = (base * hash + h.charAt(i)) % mod; 
                targetHash = (base * targetHash + n.charAt(i)) % mod;
                pow = pow * base % mod;
            }
            
            if (hash == targetHash) {
                return 0;
            }
                   
            for (int i = len; i < h.length(); i++) {
                hash = (hash * base + h.charAt(i)) % mod;
                hash = ((hash - h.charAt(i-len) * pow % mod) + mod) % mod;
                
                if (hash == targetHash) {
                    return i - len + 1;
                }
            }
            return -1;
        }
    }
}