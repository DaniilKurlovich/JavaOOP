package sample;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PlotTree<T> implements Iterable<PlotTree<T>> {

    public T data;
    public PlotTree<T> parent;
    public List<PlotTree<T>> children;

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }

    private List<PlotTree<T>> elementsIndex;

    public PlotTree(T data) {
        this.data = data;
        this.children = new LinkedList<PlotTree<T>>();
        this.elementsIndex = new LinkedList<PlotTree<T>>();
        this.elementsIndex.add(this);
    }

    public PlotTree<T> addChild(T child) {
        PlotTree<T> childNode = new PlotTree<T>(child);
        childNode.parent = this;
        this.children.add(childNode);
        this.registerChildForSearch(childNode);
        return childNode;
    }

    public int getLevel() {
        if (this.isRoot())
            return 0;
        else
            return parent.getLevel() + 1;
    }

    private void registerChildForSearch(PlotTree<T> node) {
        elementsIndex.add(node);
        if (parent != null)
            parent.registerChildForSearch(node);
    }

    public PlotTree<T> findTreeNode(Comparable<T> cmp) {
        for (PlotTree<T> element : this.elementsIndex) {
            T elData = element.data;
            if (cmp.compareTo(elData) == 0)
                return element;
        }

        return null;
    }

    @Override
    public String toString() {
        return data != null ? data.toString() : "[data null]";
    }

    @Override
    public Iterator<PlotTree<T>> iterator() {
        PlotTreeIter<T> iter = new PlotTreeIter<T>(this);
        return iter;
    }

}