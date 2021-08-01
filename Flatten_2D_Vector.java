public class Vector2D implements Iterator<Integer> {

    public List<List<Integer>> vec2d;
    int list, ind;
    public Vector2D(List<List<Integer>> vec2d) {
        // Initialize your data structure here
        this.vec2d = vec2d;
        this.list = this.ind = 0; 
    }

    @Override
    public Integer next() {
        // Write your code here
        return vec2d.get(list).get(ind++);    
    }

    @Override
    public boolean hasNext() {
        // Write your code here
        if (list >= vec2d.size()) return false;

        if (vec2d.get(list) == null || vec2d.get(list).isEmpty() || ind >= vec2d.get(list).size()) {
            list++;
            ind = 0;        
        }

        while (list < vec2d.size() && (vec2d.get(list) == null || vec2d.get(list).isEmpty()))
            list++;

        return list < vec2d.size();    
    }

    @Override
    public void remove() {
        vec2d.get(list).remove(ind);
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
