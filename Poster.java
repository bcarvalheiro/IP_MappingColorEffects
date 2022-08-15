
class Poster {
    static final int COLLECTION_SIZE = 100;
    // ATRIBUTOS
    private Layer[] collection = new Layer[COLLECTION_SIZE];
    private int pointer = 0;
    private int width = 0;
    private int height = 0;
    // CONSTRUTORES
    Poster(int width, int height) {
	this.height = height;
	this.width = width;
    }
    // INSPECTORES / GETS
    int getWidth() {
	return width;
    }

    int getHeight() {
	return height;
    }
    // Sets
    // troca de posições
    void setSwapPosition(int posInit, int posFinal) {
	if(collection[posInit]==null || collection[posFinal]==null)
	    throw new NullPointerException("As posições que está a tentar aceder não contem imagem!");
	if(posInit > COLLECTION_SIZE || posFinal > COLLECTION_SIZE)
	    throw new IllegalArgumentException ("Está a tentar aceder a uma posição fora do vetor de Layers!");
	
	
	Layer aux = collection[posInit];
	this.collection[posInit] = this.collection[posFinal];
	this.collection[posFinal] = aux;
    }
    void setBackground(Layer toPatternImg) {
	if(toPatternImg == null)
	    throw new NullPointerException("As imagens não foram carregadas");	
	// criação/troca de layer[0]	
	ColorImage aux1 = Functions.patternImage(toPatternImg.getLayer(), this.width, this.height);
	Layer aux2 = new Layer(aux1);
	collection[0] = aux2;
	if (pointer == 0) {
	    pointer++;
	}
    }
    void setNewPositionFinal(Layer newLayer) {
	if(newLayer == null)
	    throw new NullPointerException("Não selecionou uma imagem a inseri");
	collection[pointer] = newLayer;
	pointer++;
    }
    void setNewPosition(Layer newLayer, int position) {
	if(newLayer == null)
	    throw new NullPointerException("Não selecionou uma imagem a inseri");
	if (position > pointer) {
	   setNewPositionFinal(newLayer);
	   return;
	}
	
	for (int i = pointer; i != position; i--) {
	    collection[i] = collection[i-1];
	}
	collection[position] = newLayer;
	pointer++;
    }
    void setDeletePosition(int position) {
	if(collection[position]==null )
	    throw new NullPointerException("Esta Layer já se encontra vazia!");
	if(position > COLLECTION_SIZE)
	    throw new IllegalArgumentException("Não pode eliminar uma imagem que esteja fora do array!");
	
	for (int i = position; i < pointer; i++) {
	    collection[i] = collection[i + 1];
	}
	pointer--;
    }
    ColorImage getPoster() {
	//if collection[0]= null excepção !!!!
	ColorImage finalPoster = collection[0].getLayer();
	for (int x = 1; x < pointer-1; x++) 	
	    if(collection[x].getState() == true) 
	    Functions.pasteIMG(collection[x].getLayer(), finalPoster, 0, 0);	
	return finalPoster;
    }
    
    static void PosterTest() {
	Layer Layer4 = new Layer(new ColorImage("domuKun.png"), 50, 50);
	Layer Layer5 = new Layer(new ColorImage("objc3.png"), 60, 60);
	Layer Layer1 = new Layer(new ColorImage("imageB.png"));
	Layer Layer2 = new Layer(new ColorImage("imageA.png"));
	
	Layer Layer6 = new Layer(new ColorImage("objc3.png"), 75,75);
	Layer Layer3 = new Layer(new ColorImage("patternA.png"));
	//Layer Layer7= new Layer(new ColorImage("industry-compliance.png"));
	//Layer Layer8 = new Layer(new ColorImage("pattern2.png"));
	
	Poster finalPoster = new Poster(200, 200);
	finalPoster.setBackground(Layer3);
	finalPoster.setNewPosition(Layer5,30);
	finalPoster.setNewPositionFinal(Layer1);
	
	
	finalPoster.setNewPositionFinal(Layer2);
	finalPoster.setNewPositionFinal(Layer4);
	//finalPoster.setNewPosition(Layer5,2);
	//finalPoster.setNewPosition(Layer6,1);
	
	finalPoster.setNewPositionFinal(Layer6);
	//finalPoster.setBackground(Layer8);
	
	//finalPoster.setSwapPosition(0,3);
	//finalPoster.setSwapPosition(1,3);
		//ColorImage XPTO = finalPoster.getPoster();
	//Layer4.setEnable(false);
	ColorImage XPTO2 = finalPoster.getPoster();
//	//finalPoster.setDeletePosition(1);
//	//finalPoster.collection[0].getLayer();
//	//finalPoster.collection[2].getLayer();
//	finalPoster.getPoster();	
    }
}
