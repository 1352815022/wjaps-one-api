package com.donlim.aps.util.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.donlim.aps.util.DateUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName LocalDateConverter
 * @Description TODO
 * @Author p09835
 * @Date 2022/6/24 11:00
 **/
public class LocalDateConverter implements Converter<LocalDate> {

    @Override
    public Class<LocalDate> supportJavaTypeKey() {
        return null;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public LocalDate convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return LocalDate.parse(cellData.getStringValue(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public WriteCellData<?> convertToExcelData(LocalDate value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new WriteCellData<>(DateUtils.LocalDateToString(value));
    }
}
