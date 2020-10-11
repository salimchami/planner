import {CalendarHelper} from 'app/shared/services/utils/calendar-helper.service';

fdescribe('Calendar Helper Tests', function() {

    const calendarHelper = new CalendarHelper();

    function callFnAndAssertExpectedDate(dayName, clientFirstDayName, today, expectedDate) {
        const dateResult = calendarHelper.dateBasedOnTodayAndClientFirstDayFor(dayName, clientFirstDayName, today);
        expect(dateResult).toStrictEqual(expectedDate);
    }

    describe('dateByCurrentDayNameBasedOnToday - client first day monday', function() {
        it('should return week from monday', function() {
            // today : monday
            callFnAndAssertExpectedDate('WEDNESDAY', 'MONDAY', new Date('12 16 2019'), new Date('12 18 2019'));
        });

        it('should return week from tuesday', function() {
            // today : tuesday
            callFnAndAssertExpectedDate('WEDNESDAY', 'MONDAY', new Date('12 17 2019'), new Date('12 18 2019'));
        });

        it('should return week from wednesday', function() {
            // today : tuesday
            callFnAndAssertExpectedDate('WEDNESDAY', 'MONDAY', new Date('12 18 2019'), new Date('12 18 2019'));
        });

        it('should return week from thursday', function() {
            // today : tuesday
            callFnAndAssertExpectedDate('WEDNESDAY', 'MONDAY', new Date('12 19 2019'), new Date('12 18 2019'));
        });

        it('should return week from friday', function() {
            // today : tuesday
            callFnAndAssertExpectedDate('WEDNESDAY', 'MONDAY', new Date('12 20 2019'), new Date('12 18 2019'));
        });

        it('should return week from saturday', function() {
            // today : tuesday
            callFnAndAssertExpectedDate('WEDNESDAY', 'MONDAY', new Date('12 21 2019'), new Date('12 18 2019'));
        });

        it('should return week from second sunday', function() {
            // today : tuesday
            callFnAndAssertExpectedDate('WEDNESDAY', 'MONDAY', new Date('12 22 2019'), new Date('12 18 2019'));
        });

        it('should return week from first sunday', function() {
            // today : tuesday
            callFnAndAssertExpectedDate('WEDNESDAY', 'MONDAY', new Date('12 15 2019'), new Date('12 11 2019'));
        });
    });

    describe('dateByCurrentDayNameBasedOnToday - client first day sunday', function() {
        it('should return week from monday', function() {
            // today : monday
            callFnAndAssertExpectedDate('WEDNESDAY', 'SUNDAY', new Date('12 16 2019'), new Date('12 18 2019'));
        });

        it('should return week from tuesday', function() {
            // today : tuesday
            callFnAndAssertExpectedDate('WEDNESDAY', 'SUNDAY', new Date('12 17 2019'), new Date('12 18 2019'));
        });

        it('should return week from wednesday', function() {
            // today : tuesday
            callFnAndAssertExpectedDate('WEDNESDAY', 'SUNDAY', new Date('12 18 2019'), new Date('12 18 2019'));
        });

        it('should return week from thursday', function() {
            // today : tuesday
            callFnAndAssertExpectedDate('WEDNESDAY', 'SUNDAY', new Date('12 19 2019'), new Date('12 18 2019'));
        });

        it('should return week from friday', function() {
            // today : tuesday
            callFnAndAssertExpectedDate('WEDNESDAY', 'SUNDAY', new Date('12 20 2019'), new Date('12 18 2019'));
        });

        it('should return week from saturday', function() {
            // today : tuesday
            callFnAndAssertExpectedDate('WEDNESDAY', 'SUNDAY', new Date('12 21 2019'), new Date('12 18 2019'));
        });

        it('should return week from second sunday', function() {
            // today : tuesday
            callFnAndAssertExpectedDate('WEDNESDAY', 'SUNDAY', new Date('12 22 2019'), new Date('12 25 2019'));
        });

        it('should return week from first sunday', function() {
            // today : tuesday
            callFnAndAssertExpectedDate('WEDNESDAY', 'SUNDAY', new Date('12 15 2019'), new Date('12 18 2019'));
        });
    });

    // describe('timeSlotDurationInMinutes - valid', function() {
    //     it.only('should return week from monday', function() {
    //         const schoolClassTimeSlot = new SchoolClassTimeSlot();
    //         schoolClassTimeSlot.subjectId = '1';
    //         schoolClassTimeSlot.startTime = new Time('14', '0', '0', 'PM');
    //         schoolClassTimeSlot.endTime = new Time('15', '0', '0', 'PM');
    //         const durationInMinutes = calendarHelper.timeSlotDurationInMinutes(schoolClassTimeSlot, new Date(2020, 1, 20, 14, 0, 0, 0));
    //         expect(durationInMinutes).toStrictEqual(60);
    //     });
    // });
});
