package task4;

class Music implements Comparable<Music> {
    private final String name;
    private final double duration;

    public Music(String name, double duration) {
        this.name = name;
        this.duration = duration;
    }

    public double getDuration() {
        return duration;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.name.hashCode();
        result = 31 * result + Double.hashCode(duration);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(this.getClass() != obj.getClass() && obj == null) return false;

        Music anotherMusic = (Music) obj;
        if(this.duration != anotherMusic.duration) return false;
        else if(!this.name.equals(anotherMusic.name)) return false;
        return true;
    }

    @Override
    public int compareTo(Music o) {
        int durationComparison = Double.compare(this.duration, o.duration);
        if(durationComparison == 0){
            int nameComparison = this.name.compareTo(o.name);
            if(nameComparison != 0) return nameComparison;
        }
        return durationComparison;
    }

    @Override
    public String toString() {
        return "Music{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }
}
