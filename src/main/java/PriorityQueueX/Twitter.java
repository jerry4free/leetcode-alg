package PriorityQueueX;

import java.util.*;

/**
 * 355. 设计推特
 */
public class Twitter {

    /**
     * tweet用了一个int[]来表示：
     *
     * 其实用对象表示可读性更好, scala case class
     * case class User(int userId, Set[Int] followee, Tweet head)
     * case class Tweet(int tweetId, int timestamp, int next)
     */
    // follow -> followee set
    HashMap<Integer, HashSet<Integer>> followMap;
    // user -> tweets
    HashMap<Integer, List<int[]>> tweetMap;
    Integer incId;

    public Twitter() {
        incId = 0;
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        List<int []> tweets = tweetMap.getOrDefault(userId, new ArrayList<>());
        int[] tweet = new int[]{tweetId, incId++};
        tweets.add(tweet);
        tweetMap.put(userId, tweets);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> users = new ArrayList<>();
        users.add(userId);
        if (followMap.containsKey(userId)) {
            users.addAll(followMap.get(userId));
        }

        List<Integer> result = new ArrayList<>();
        // 优先队列进行排序，从N个有序列表，取Top10.
        // int[3]: userId, hisTweetId, hisOffset, incId
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o2[3] - o1[3]);

        // add all header to queue;
        for (int user: users) {
            if (tweetMap.containsKey(user)) {
                List<int []> tweets = tweetMap.get(user);
                int offset = tweets.size() - 1;
                int[] tweet = tweets.get(offset);
                int[] data = new int[]{user, tweet[0], offset,tweet[1]};
                q.add(data);
            }
        }

        //
        while (result.size() < 10 && !q.isEmpty()) {
            int[] data = q.poll();
            int user = data[0];
            int tweetId = data[1];
            int offset = data[2];
            List<int []> tweets = tweetMap.get(user);
            if (offset >= 1) {
                int[] tweet = tweets.get(offset-1);
                int[] nextData = new int[]{user, tweet[0], offset-1, tweet[1]};
                q.add(nextData);
            }

            result.add(tweetId);
        }

        return result;
    }

    public void follow(int followerId, int followeeId) {
        HashSet<Integer> followee = followMap.getOrDefault(followerId, new HashSet<>());
        followee.add(followeeId);
        followMap.put(followerId, followee);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followMap.containsKey(followerId)) {
            followMap.get(followerId).remove(followeeId);
        }
    }

    public static void main(String[] args) {
/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
    }
}
