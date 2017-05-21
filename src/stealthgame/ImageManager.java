package stealthgame;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageManager {
	private class ImageInfo
	{
		String filename;
		Image image;
	}
	
	private ArrayList<ImageInfo> imageInfos;
	
	public ImageManager()
	{
		imageInfos = new ArrayList<ImageInfo>();
	}
	
	public Image get(String filename)
	{
		for(int i = 0; i < imageInfos.size(); i++)
		{
			if(imageInfos.get(i).filename.equals(filename))
			{
				return imageInfos.get(i).image;
			}
		}
		
		ImageInfo info = new ImageInfo();
		info.filename = filename;
		try {
			info.image = new Image(filename);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		imageInfos.add(info);
		return info.image;
	}
	
	private static ImageManager instance = null;
	public static ImageManager getInstance()
	{
		if(instance == null)
		{
			instance = new ImageManager();
		}
		
		return instance;
	}
}
