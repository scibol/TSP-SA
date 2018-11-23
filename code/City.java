public class City {
    private long x;
    private long y;
    private int n;

    public City(long x, long y, int n){
        this.n = n;
        this.x = x;
        this.y = y;
    }
    public int getN() {return this.n;}

    public long getX(){
        return this.x;
    }

    public long getY(){
        return this.y;
    }

    public double distanceTo(City node){
        long deltaX = this.getX() - node.getX();
        long deltaY = this.getY() - node.getY();
        long deltaY2 = deltaY * deltaY;
        long deltaX2 = deltaX * deltaX;
        return Math.round(Maths.sqrt(deltaX2 + deltaY2));
    }
}
