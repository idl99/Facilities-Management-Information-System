package Entities;

import Entities.Furniture.FurnitureItem;
import Entities.Space.Space;
import Entities.Space.SpaceType;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

public class FmoReport {

    private BuildingFloor buildingFloor;
    private int totalAssetValue;
    private Document document;

    public FmoReport(BuildingFloor buildingFloor){
        this.buildingFloor = buildingFloor;
        // 1 Open file
        // 2 Write to file
            // Write report headers
            // Take floor by floor
            // Print floor detiails
            // Print furniture details
            // Repeat for each floor
        // 3 Close to file
        try {
            createPdf();
            addHeader();
            addFloorDetails();
            addReportDetail();
            addReportFooter();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    public void createPdf()
            throws DocumentException, IOException {
        // step 1
        document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream("FmoReport.pdf"));
        // step 3
        document.open();
        // step 5
    }

    public void addHeader() throws DocumentException, IOException{

        Chunk titleChunk =  new Chunk("FMO Asset Report");
        Chunk dateChunk = new Chunk(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        Paragraph title = new Paragraph();
        title.setAlignment(Element.ALIGN_CENTER);
        title.add(titleChunk);
        title.add(Chunk.NEWLINE);
        document.add(title);

        Paragraph date = new Paragraph();
        date.setAlignment(Element.ALIGN_RIGHT);
        date.setSpacingAfter(18f);

        date.add("Generated on: "+dateChunk);
        date.add(Chunk.NEWLINE);

        document.add(date);
    }

    public void addFloorDetails() throws DocumentException{

        Chunk campusChunk = new Chunk("Campus: "+buildingFloor.getBuildingName());
        Chunk buildingChunk = new Chunk("Building: "+buildingFloor.getBuildingNumber());
        Chunk floorChunk = new Chunk("Floor: "+buildingFloor.getFloorNumber());

        Paragraph floorDetails = new Paragraph();
        floorDetails.setSpacingAfter(36f);

        floorDetails.add(campusChunk);
        floorDetails.add(Chunk.NEWLINE);
        floorDetails.add(buildingChunk);
        floorDetails.add(Chunk.NEWLINE);
        floorDetails.add(floorChunk);

        document.add(floorDetails);
    }

    public void addReportDetail() throws DocumentException{
        for(Space space: Space.getSpaceByBuildingFloor(this.buildingFloor.getBuildingNumber(),
                this.buildingFloor.getFloorNumber())){
            addSpaceDetails(space);
        }
    }

    public void addSpaceDetails(Space space) throws DocumentException{

        Chunk spaceChunk = new Chunk("Space Id: "+space.getSpaceId());
        Chunk spaceTypeChunk = new Chunk("Space Type: "+space.getType()
                .toString().replaceAll("_"," "));
        Chunk occupyingDeptChunk = new Chunk("Occupying Dept: "+space.getOccupyingDepartment());

        Chunk occupantChunk = new Chunk("Occupant: ");
        if(space.getOccupant_EmployeeId().equals("") &&
                space.getType() == SpaceType.OFFICE_SPACE)
            occupantChunk.append("Vacant");
        else  occupantChunk.append(space.getOccupant_EmployeeId());

        Paragraph spaceDetails = new Paragraph();
        spaceDetails.setTabSettings(new TabSettings(300f));
        spaceDetails.setSpacingAfter(18f);

        spaceDetails.add(spaceChunk);
        spaceDetails.add(Chunk.TABBING);

        spaceDetails.add(spaceTypeChunk);
        spaceDetails.add(Chunk.NEWLINE);

        spaceDetails.add(occupyingDeptChunk);
        spaceDetails.add(Chunk.TABBING);

        spaceDetails.add(occupantChunk);
        spaceDetails.add(Chunk.NEWLINE);

        document.add(spaceDetails);

        addFurnitureDetails(space);

    }

    public void addFurnitureDetails(Space space) throws DocumentException{

        int totalRoomCost = 0;

        Chunk title = new Chunk("Furniture Assets");
        PdfPTable table = new PdfPTable(5);
        table.setWidths(new int[]{1,3,3,3,2});
        table.setWidthPercentage(100);

        table.addCell("#");
        table.addCell("Barcode");
        table.addCell("Description");
        table.addCell("Acquisition Date");
        table.addCell("Cost");

        int numOfItems = 0;
        List<FurnitureItem> list = FurnitureItem.getFurnitureBySpace(space.getSpaceId(),space.getBuildingNumber());
        for(FurnitureItem item: FurnitureItem.getFurnitureBySpace(
                space.getSpaceId(),space.getBuildingNumber())){
            table.addCell(String.valueOf(++numOfItems));
            table.addCell(item.getBarcode());
            table.addCell(
                    String.valueOf(item.getMaterial()).replaceAll("_"," ")
                            .replaceAll("null","") +
                            " " +
                            String.valueOf(item.getItemType()).replaceAll("_"," "));
            table.addCell(new SimpleDateFormat("dd/MM/yyyy").format(item.getPurchase().getDate()));
            table.addCell(String.valueOf(item.getPurchase().getCost()));
            totalRoomCost+=item.getPurchase().getCost();
        }

        Chunk chunkTotalRoomCost = new Chunk("Room Total: "+totalRoomCost);
        this.totalAssetValue += totalRoomCost;

        Paragraph furnitureItems = new Paragraph();
        furnitureItems.setSpacingAfter(50f);
        furnitureItems.add(title);
        furnitureItems.add(table);
        furnitureItems.add(chunkTotalRoomCost);

        document.add(furnitureItems);
    }

    public void addReportFooter() throws DocumentException{

        Chunk chunkTotalSpace = new Chunk("Total Space occupied: "+this.buildingFloor.getUFA());
        Chunk chunkTotalAssetValue = new Chunk("Total Asset Value: "+this.totalAssetValue);

        Paragraph footerDetail = new Paragraph();
        footerDetail.add(chunkTotalSpace);
        footerDetail.add(Chunk.NEWLINE);
        footerDetail.add(chunkTotalAssetValue);

        document.add(footerDetail);
    }



}
