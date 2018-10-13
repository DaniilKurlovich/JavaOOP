package sample;
import java.util.Iterator;

public class PlotTreeIter<T> implements Iterator<PlotTree<T>> {

    enum ProcessStages {
        ProcessParent, ProcessChildCurNode, ProcessChildSubNode
    }

    private PlotTree<T> plotTree;

    public PlotTreeIter(PlotTree<T> plotTree) {
        this.plotTree = plotTree;
        this.doNext = ProcessStages.ProcessParent;
        this.childrenCurNodeIter = plotTree.children.iterator();
    }

    private ProcessStages doNext;
    private PlotTree<T> next;
    private Iterator<PlotTree<T>> childrenCurNodeIter;
    private Iterator<PlotTree<T>> childrenSubNodeIter;

    @Override
    public boolean hasNext() {

        if (this.doNext == ProcessStages.ProcessParent) {
            this.next = this.plotTree;
            this.doNext = ProcessStages.ProcessChildCurNode;
            return true;
        }

        if (this.doNext == ProcessStages.ProcessChildCurNode) {
            if (childrenCurNodeIter.hasNext()) {
                PlotTree<T> childDirect = childrenCurNodeIter.next();
                childrenSubNodeIter = childDirect.iterator();
                this.doNext = ProcessStages.ProcessChildSubNode;
                return hasNext();
            }

            else {
                this.doNext = null;
                return false;
            }
        }

        if (this.doNext == ProcessStages.ProcessChildSubNode) {
            if (childrenSubNodeIter.hasNext()) {
                this.next = childrenSubNodeIter.next();
                return true;
            }
            else {
                this.next = null;
                this.doNext = ProcessStages.ProcessChildCurNode;
                return hasNext();
            }
        }

        return false;
    }

    @Override
    public PlotTree<T> next() {
        return this.next;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
