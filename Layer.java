
class Layer {
    // Attributes
    private String name = null;
    private boolean enable = false;
    private double scale = 1.0;
    private int x = 0;
    private  int y = 0;
    private ColorImage img;  

    
    //Constructors
    Layer(String name, double scale, int x, int y, ColorImage img) {
	this.enable = true;
	this.name = name;
	this.scale = scale;
	this.x = x;
	this.y = y;
	this.img = img;
    }   
    Layer(ColorImage img, int x, int y) {	
	this(null,1.0,x,y,img);
    }
    Layer(ColorImage img) {
	this(null,1.0,0,0,img);
    }
//Inspectors / GETS
    ColorImage getImg() {
	return this.img;
    }   
    int getX() {
	return this.x;
    }
    int getY() {
	return this.y;
    }
    boolean getState() {
	if (this.img == null)
	    throw new NullPointerException("Não existe Imagem!");
	return this.enable;
    }
    String getName() {
	return this.name;
    }
    double getFactor() {
	return this.scale;
    }    
    ColorImage getLayer() {
	ColorImage finalLayer = new ColorImage(Functions.scaleImage(img, scale).getWidth() + this.x,
		Functions.scaleImage(img, scale).getHeight() + this.y);
	for (int x = 0; x != finalLayer.getWidth(); x++) {
	    for (int y = 0; y != finalLayer.getHeight(); y++) {
		finalLayer.setColor(x, y, Color.TRANSPARENTE);
	    }
	}
	Functions.pasteIMG(Functions.scaleImage(this.img, this.scale), finalLayer, this.x, this.y);
	return finalLayer;
    }
//Modifiers / Sets
    void setName(String name) {
	if(name == null)
	    throw new IllegalArgumentException("Não pode passar um nome nulo");
	this.name = name;

    }
    void setFactor(double factor) {
	if(factor < 0)
	    throw new IllegalArgumentException("O fator de escala deve ser superior a 0");
	this.scale = factor;
    }
    void setCoordinates(int x, int y) {
	if (x < 0 || y < 0)
	    throw new IllegalArgumentException("As coordenadas devem ser numeros maiores que ou iguais a 0");
	this.x = x;
	this.y = y;
    }   
    void setEnable(boolean enable) {
	this.enable = enable;
    }
//TESTERS!
    static void LayerTester() {
	ColorImage imageA = new ColorImage("imageA.png");
	Layer layerTest = new Layer("NomeA", 2.0, 20, 20, imageA);
	layerTest.getFactor();
	layerTest.getName();
	layerTest.getState();
	layerTest.getX();
	layerTest.getY();
	layerTest.setName("Bruno");
	layerTest.setFactor(1.25);
	layerTest.setCoordinates(30, 30);	
	layerTest.getLayer();	
    }
}
