
class Functions {    
    static ColorImage pasteIMG(ColorImage imageA, ColorImage imageB, double x0, double y0) {
	if(x0 > imageB.getWidth() || y0 > imageB.getHeight())
	    throw new NullPointerException ("As coordenadas que está a tentar colar a imagem original, não se encontram na imagem destino");
	
	if (x0 < 0 || y0 < 0)
	    throw new IllegalArgumentException("As coordenadas devem ser numeros maiores que ou iguais a 0");
	if(imageA == null || imageB == null)
	    throw new NullPointerException("As imagens não foram carregadas");
	
	for (int x = 0; x != imageA.getWidth(); x++) {
	    for (int y = 0; y != imageA.getHeight(); y++) {
		if (imageA.getColor(x, y).isEqualTo(Color.TRANSPARENTE) == false) {
		    if ((int) (x + x0) < imageB.getWidth() && (int) (y + y0) < imageB.getHeight())
			imageB.setColor((int) (x + x0), (int) (y + y0), imageA.getColor(x, y));
		}
	    }
	}
	return imageB;
    }
    static ColorImage patternImage(ColorImage toPatterImg, int x0, int y0) {
	if (x0 < 0 || y0 < 0)
	    throw new IllegalArgumentException("As coordenadas devem ser numeros maiores que ou iguais a 0");
	if(toPatterImg == null)
	    throw new NullPointerException("As imagens não foram carregadas");
	
	ColorImage patternedImage = new ColorImage(x0, y0);
	if (toPatterImg.getWidth() > x0 || toPatterImg.getHeight() > y0) {
	   x0 = toPatterImg.getWidth();
	   y0 = toPatterImg.getHeight();
	}
	for(int i = 0; i!=patternedImage.getWidth(); i++) {
	    for(int j = 0; j!=patternedImage.getHeight(); j++) {
		patternedImage.setColor(i, j, Color.TRANSPARENTE);
	    }
	}
	for (int x = 0; x < patternedImage.getWidth(); x += toPatterImg.getWidth()) {
	    for (int y = 0; y < patternedImage.getHeight(); y += toPatterImg.getHeight()) {
		if(x < patternedImage.getWidth() && y < patternedImage.getHeight())
		pasteIMG(toPatterImg, patternedImage, x, y);
	    }
	}
	return patternedImage;
    }
    static ColorImage scaleImage(ColorImage toScaleImg, double scaleFactor) {
	if(scaleFactor <= 0)
	    throw new IllegalArgumentException("O fator de escala deve ser superior a 0");
	if(toScaleImg == null)
	    throw new NullPointerException("As imagens não foram carregadas");
	int newImgWidth = (int) (toScaleImg.getWidth() * scaleFactor);
	int newImgHeigth = (int) (toScaleImg.getHeight() * scaleFactor);
	if (toScaleImg.getWidth() * scaleFactor < 1 || toScaleImg.getHeight() * scaleFactor < 1) {
	    newImgWidth = 1;
	    newImgHeigth = 1;
	}
	ColorImage scaledImage = new ColorImage(newImgWidth, newImgHeigth);
	for (int x = 0; x < scaledImage.getWidth(); x++) {
	    for (int y = 0; y < scaledImage.getHeight(); y++) {
		int x0 = (int) (x / scaleFactor);
		int y0 = (int) (y / scaleFactor);
		scaledImage.setColor(x, y, toScaleImg.getColor(x0, y0));
	    }
	}
	return scaledImage;
    }
    @SuppressWarnings("unused")
    static ColorImage drawCircle(ColorImage toCropCircleImg, int x0, int y0, int radius) {
	if(x0<0 || y0 < 0)
	    throw new IllegalArgumentException("As coordenadas devem ser numeros maiores que ou iguais a 0");
	if(x0 > toCropCircleImg.getWidth() || y0 > toCropCircleImg.getHeight())
	    throw new NullPointerException ("As coordenadas que está a tentar retirar o circulo, não se encontram na imagem que pretende");
	if(toCropCircleImg == null)
	    throw new NullPointerException("As imagens não foram carregadas");
	if(radius > (toCropCircleImg.getWidth()/2) || radius > (toCropCircleImg.getHeight()/2))
	    throw new IllegalArgumentException("O raio é maior do que a imagem!");
	
//	if (x0 + radius > toCropCircleImg.getWidth() || y0 + radius > toCropCircleImg.getHeight())
//	    throw new IllegalArgumentException("Não pode defenir este tamanho!");    
	ColorImage circledImage = new ColorImage(radius * 2, radius * 2);
	for (int x = 0, xBase = x0 - radius; x != circledImage.getWidth(); x++, xBase++) {
	    for (int y = 0, yBase = y0 - radius; y != circledImage.getHeight(); y++, yBase++) {

		double distance = Math.sqrt(Math.pow(x - radius, 2) + Math.pow(y - radius, 2));

		if (distance < radius) {
		    if (xBase >= 0 && xBase < toCropCircleImg.getWidth() && yBase >= 0
			    && yBase < toCropCircleImg.getHeight())
			circledImage.setColor(x, y, toCropCircleImg.getColor(xBase, yBase));
		    else {
			circledImage.setColor(x, y, Color.TRANSPARENTE);
		    }
		} else {
		    circledImage.setColor(x, y, Color.TRANSPARENTE);
		}
	    }
	}
	return circledImage;
    }
    static ColorImage greyScale(ColorImage toGreyScale) {
	if(toGreyScale == null)
	    throw new NullPointerException("As imagens não foram carregadas");
	
	ColorImage greyScaledImg = new ColorImage(toGreyScale.getWidth(), toGreyScale.getHeight());
	for (int x = 0; x != toGreyScale.getWidth(); x++) {
	    for (int y = 0; y != toGreyScale.getHeight(); y++) {
		Color corOriginal = toGreyScale.getColor(x, y);
		int greyColor = (int) ((0.3 * corOriginal.getR()) + (0.59 * corOriginal.getG())
			+ (0.11 * corOriginal.getB()));
		Color greyColored = new Color(greyColor, greyColor, greyColor);
		greyScaledImg.setColor(x, y, greyColored);
	    }
	}
	return greyScaledImg;
    }    
 //TESTERS   
    static void pasteIMGTest(int x, int y) {
	ColorImage imageA = new ColorImage("imageA.png");
	ColorImage imageB = new ColorImage("imageB.png");
	pasteIMG(imageB, imageA, x, y);
    }    
    static void patternImageTest(int x, int y) {
	ColorImage toPatterImg = new ColorImage("imageA.png");
	patternImage(toPatterImg, x, y);
    }    
    static void scaleImageTEST(double scaleFactor) {
	ColorImage toScale = new ColorImage("imageA.png");
	scaleImage(toScale, scaleFactor);
    }    
    static void drawCircleTEST(int x0, int y0, int radius) {
	ColorImage toCropCircleImg = new ColorImage("imageB.png");
	drawCircle(toCropCircleImg, x0, y0, radius);
    }   
    static void greyScaleTEST() {
	ColorImage toGreyScale = new ColorImage("imageA.png");
	greyScale(toGreyScale);
    }
}
