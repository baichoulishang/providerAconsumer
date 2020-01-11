package com.utils;

/**
 * @author 陈宜康
 * @date 2019/8/4 13:37
 * @forWhat
 */
public class TrieTree {
    //前缀树的结构
    public static class TrieNode {
        //表示扫过当前结点多少次
        public int path;
        //表示以当前结点结尾的次数
        public int end;

        //表示当前结点的下一个结点（从a-z）  同时，数组中对应的位置就是a-z字母的顺序
        // 如果想表示其他类型的，可以使用HashMap<Integer, TrieNode> nexts;
        // key表示的是边的ASCII码，value表示对应的下一个结点
        public TrieNode[] nexts;


        public TrieNode() {
            path = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }


    public static class Trie {
        //前缀树的头结点 为空
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public static void main(String[] args) {
            Trie trie = new Trie();
            trie.insert("今天天气啊");
            trie.insert("今天天气不错");
            trie.insert("今天不错");
            trie.insert("今天天气可以");
            trie.insert("天气可以");
            trie.prefixNumber(("今天天气"));
        }

        /**
         * 将一个字符串加入前缀树，即向前缀树中添加路径和结点，
         * 首先检查前缀树中是否含有当前结点，
         * 如果存在的话，则将其路径上的path++，end++，
         * 如果不存在某几个结点的话，则创建出来
         *
         * @param str
         */
        public void insert(String str) {
            if (str == null || str.length() == 0) return;
            str = PinYinUtil.getFullSpell(str);
            char[] ch = str.toCharArray();

            TrieNode trieNode = root;

            for (int i = 0; i < ch.length; i++) {
                int index = ch[i] - 'a';
                //该元素不存在，则创建出来
                if (trieNode.nexts[index] == null) {
                    trieNode.nexts[index] = new TrieNode();
                }
                trieNode = trieNode.nexts[index];
                trieNode.path++;
            }
            trieNode.end++;
        }

        /**
         * 删除指定字符串对应在前缀树中的路径
         * 在前缀树中查找，然后依次将其path-1，
         * 当path==0时，直接将其置为null即可（后面的结点都可以不要了）
         *
         * @param str
         */
        public void delete(String str) {
            if (str == null || str.length() == 0) return;
            char[] ch = str.toCharArray();
            TrieNode trieNode = root;

            for (int i = 0; i < ch.length; i++) {
                int index = ch[i] - 'a';
                if (--trieNode.nexts[index].path == 0) {
                    trieNode.nexts[index] = null;
                    return;
                }
                trieNode = trieNode.nexts[index];
            }
            trieNode.end--;
        }

        /**
         * 查询一个字符串在前缀树中出现了多少次
         * 如果一路下来都存在，则返回end中的值，
         * 如果过程中含有不存在的值，则返回0
         *
         * @param str
         * @return
         */
        public int search(String str) {
            if (str == null || str.length() == 0) return 0;

            char[] ch = str.toCharArray();

            TrieNode trieNode = root;
            for (int i = 0; i < ch.length; i++) {
                int index = ch[i] - 'a';
                if (trieNode.nexts[index] == null) return 0;
                trieNode = trieNode.nexts[index];
            }
            return trieNode.end;
        }

        /**
         * 查找以str为前缀的字符有多少
         * 即查找以str为路径的path是多少
         *
         * @param str
         * @return
         */
        public int prefixNumber(String str) {
            if (str == null || str.length() == 0) return 0;
            str = PinYinUtil.getFullSpell(str);
            char[] ch = str.toCharArray();
            TrieNode trieNode = root;

            for (int i = 0; i < ch.length; i++) {
                int index = ch[i] - 'a';
                if (trieNode.nexts[index] == null) return 0;
                trieNode = trieNode.nexts[index];
            }
            getString(trieNode);
            return trieNode.path;
        }

        public void getString(TrieNode trieNode) {
            TrieNode[] nodes = trieNode.nexts;
            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i] != null) {
                    char a = (char) (65 + i);
                    System.out.println(a);
                    getString(nodes[i]);
                }
            }
        }

    }
}
