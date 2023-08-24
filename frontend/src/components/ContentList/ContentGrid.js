import Grid from "@toast-ui/react-grid";
import React from 'react'
import grid from "bootstrap/js/src/dom/event-handler";


const ContentGrid = React.forwardRef((props, ref) => {
/*    if (props.highlight === true) {
        tableOptions.cell.focused = { background: "none", border: "none" };
        tableOptions.row = {
            background: "white",
            hover: {
                background: "#a2bbeb !important"
            }
        };
    } else {
        tableOptions.cell.focused = { background: "none", border: "#0f75bc" };
        tableOptions.row = {
            odd: {
                background: "white"
            },
            even: {
                background: "#dcddde"
            }
        };
    }*/

    // const handleGridRowClick = (event, row) => {
    //     // 전달된 onGridRowClick 핸들러 호출하여 클릭된 행의 데이터 전달
    //     if (props.onLectureClick) {
    //         props.onLectureClick(row);
    //     }
    // }

    const key = (event) => {
        const rowData = props.data[event.rowKey]; // 클릭한 행의 데이터 얻기
        console.log(rowData);
    }


    //TuiGrid.applyTheme(null, tableOptions);
    return (
        <>
            <Grid
                ref={ref}
                rowHeight={25}
                bodyHeight={100}
                onGridRowClick={key}
                {...props}
            />
        </>
    );
});

export default ContentGrid;