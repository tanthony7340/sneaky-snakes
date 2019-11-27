import flash.display.Sprite;
import flash.display.Bitmap;
import openfl.Assets;

class Tile extends Sprite {

    private var image:Bitmap;

    public function new() {
        super();

        image = new Bitmap(Assets.getBitmapData("assets/grassLeftBlock.png"));
        addChild(image);
    }
}