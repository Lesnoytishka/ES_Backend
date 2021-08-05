package ea_and_st.dto.forMobile;

import com.ea_and_st.dto.ShowplaceDtoForListConverter;

import java.util.ArrayList;
import java.util.List;

public class FormForLists {
    public List<ShowplaceDTOForListsAllInOne> mergeLists(
            List<ShowplaceDtoForListConverter> listForYou,
            List<ShowplaceDtoForListConverter> listMostPopular,
            List<ShowplaceDtoForListConverter> listLeastPopular,
            List<ShowplaceDtoForListConverter> showplaceListSmart,
            List<FavoriteShowplaceDTO> favoriteShowplaceList) {
        List<ShowplaceDTOForListsAllInOne> listsAllInOnes = new ArrayList<>();
        for (ShowplaceDtoForListConverter dtoForLists : listForYou) {
            ShowplaceDTOForListsAllInOne newDto = buildShowplaceDto(dtoForLists);
            newDto.setForYou(true);

            for (ShowplaceDtoForListConverter s : listMostPopular) {
                if (newDto.getId().longValue() == s.getShowplaceId().longValue()) {
                    newDto.setPopular(true);
                    listMostPopular.remove(s);
                    break;
                }
            }
            removeDuplicatedShowplacesFromMergedListAndCheckIt(listLeastPopular, showplaceListSmart, listsAllInOnes, newDto);
        }

        for (ShowplaceDtoForListConverter dtoPopularLists : listMostPopular) {
            ShowplaceDTOForListsAllInOne newDto = buildShowplaceDto(dtoPopularLists);
            newDto.setPopular(true);

            removeDuplicatedShowplacesFromMergedListAndCheckIt(listLeastPopular, showplaceListSmart, listsAllInOnes, newDto);
        }

        for (ShowplaceDtoForListConverter dtoUncrowdedList : listLeastPopular) {
            ShowplaceDTOForListsAllInOne newDto = buildShowplaceDto(dtoUncrowdedList);
            newDto.setUncrowded(true);
            for (ShowplaceDtoForListConverter s : showplaceListSmart) {
                if (newDto.getId().longValue() == s.getShowplaceId().longValue()) {
                    newDto.setNearest(true);
                    showplaceListSmart.remove(s);
                    break;
                }
            }
            listsAllInOnes.add(newDto);
        }

        for (ShowplaceDtoForListConverter dtoSmart : showplaceListSmart) {
            ShowplaceDTOForListsAllInOne newDto = buildShowplaceDto(dtoSmart);
            newDto.setNearest(true);
            listsAllInOnes.add(newDto);
        }
        for (ShowplaceDTOForListsAllInOne allInOne : listsAllInOnes) {
            for (FavoriteShowplaceDTO favoriteShowplaceDTO : favoriteShowplaceList) {
                if (allInOne.getId().equals(favoriteShowplaceDTO.getShowplaceId())) allInOne.setFavorite(true);
                break;
            }
        }
        return listsAllInOnes;
    }

    private void removeDuplicatedShowplacesFromMergedListAndCheckIt(
            List<ShowplaceDtoForListConverter> listLeastPopular,
            List<ShowplaceDtoForListConverter> showplaceListSmart,
            List<ShowplaceDTOForListsAllInOne> listsAllInOnes,
            ShowplaceDTOForListsAllInOne newDto
    ) {
        for (ShowplaceDtoForListConverter s : listLeastPopular) {
            if (newDto.getId().longValue() == s.getShowplaceId().longValue()) {
                newDto.setUncrowded(true);
                listLeastPopular.remove(s);
                break;
            }
        }
        for (ShowplaceDtoForListConverter s : showplaceListSmart) {
            if (newDto.getId().longValue() == s.getShowplaceId().longValue()) {
                newDto.setNearest(true);
                showplaceListSmart.remove(s);
                break;
            }
        }
        listsAllInOnes.add(newDto);
    }

    private ShowplaceDTOForListsAllInOne buildShowplaceDto(ShowplaceDtoForListConverter converter) {
        return new ShowplaceDTOForListsAllInOne.Builder()
                .setId(converter.getShowplaceId())
                .setGpsLat(converter.getGpsLat())
                .setGpsLong(converter.getGpsLong())
                .setMainPhotoPath(converter.getMainPhotoPath())
                .setPopularity(converter.getPopularity().floatValue())
                .setRating(converter.getRating().floatValue())
                .setNearestMetro(converter.getNearestMetro())
                .setTitleEn(converter.getTitleEn())
                .setTitleRu(converter.getTitleRu())
                .setCategory_1(converter.getCategory1())
                .setCategory_2(converter.getCategory2())
                .build();
    }
}
