package chain

object TestChainofResponsibility extends App {
		val textHandler: Handler = new TextFileHandler("Text Handler")
		val docHandler: Handler = new DocFileHandler("Doc Handler")
		val excelHandler: Handler = new ExcelFileHandler("Excel Handler")
		val audioHandler: Handler = new AudioFileHandler("Audio Handler")
		val videoHandler: Handler = new VideoFileHandler("Video Handler")
		val imageHandler: Handler = new ImageFileHandler("Image Handler")
		
		textHandler.setNextHandler(docHandler)
		docHandler.setNextHandler(excelHandler)
		excelHandler.setNextHandler(audioHandler)
		audioHandler.setNextHandler(videoHandler)
		videoHandler.setNextHandler(imageHandler)
		
		
		
		var file = File("Abc.mp3", "audio", "C:")
		textHandler.process(file)
		
		println("---------------------------------")
		
		file = File("Abc.jpg", "video", "C:")
		textHandler.process(file)
		
		println("---------------------------------")
		
		file = File("Abc.doc", "doc", "C:")
		textHandler.process(file)
		
		println("---------------------------------")
		
		file = File("Abc.bat", "bat", "C:")
		textHandler.process(file)
}
