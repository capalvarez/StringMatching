package suffixTree;

public class SuffixTreeBuilder {
	String text;
	Node treeRoot;
	
	public SuffixTreeBuilder(String text){
		this.text = text;
	}
	
	private void constructTree(){
		treeRoot = new Node(0,0,0,null,26);
		int m = text.length();
		
		
		for(int i=1;i<m;i++){
			int jI = 1;
			
			for(int j=jI;j<=i+1;j++){
				Node n = treeRoot.findNode(j, i);	
				
				if(n.isLeaf()){
					n.implicitAdd();
					continue;
				}
								
				if(n.hasChild(i+1)){
					jI = i;
					break;
				}
				
				Node newChild = new Node()
				
				
				
				
				n.addChild(i+1);
		
			}
		}
		
		
	}
	
}
